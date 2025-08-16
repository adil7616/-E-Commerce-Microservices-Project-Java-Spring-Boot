package com.Adil.AuthenticationService.Service;

import ch.qos.logback.classic.Logger;
import com.Adil.AuthenticationService.Exceptions.UserExists;
import com.Adil.AuthenticationService.Exceptions.UserNotFound;
import com.Adil.AuthenticationService.Exceptions.WrongPassword;
import com.Adil.AuthenticationService.Model.Role;
import com.Adil.AuthenticationService.Model.Session;
import com.Adil.AuthenticationService.Model.SessionStatus;
import com.Adil.AuthenticationService.Model.User;
import com.Adil.AuthenticationService.Repository.AuthenticationRepository;
import com.Adil.AuthenticationService.Repository.SessionRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private SecretKey secretKey= Jwts.SIG.HS256.key().build();
    private final SessionRepository sessionRepository;

    public AuthenticationService(AuthenticationRepository authenticationRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                                 SessionRepository sessionRepository)
    {
        this.authenticationRepository=authenticationRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.sessionRepository = sessionRepository;
    }
    public boolean registerUser(String email,String password)
    {
        Optional<User> findUser=authenticationRepository.findByEmail(email);
        if(findUser.isPresent())
        {
            throw new UserExists("User already registers with this email. ");
        }

        User user= new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        authenticationRepository.save(user);
        return true;
    }

    public String loginUser(String email, String password, String client_Info)
    {
        Optional<User> optUser= authenticationRepository.findByEmail(email);
        if (optUser.isEmpty())
        {
            throw new UserNotFound("User not registered with this email.");
        }
        boolean matchPassword = bCryptPasswordEncoder.matches(password,optUser.get().getPassword());
        if(matchPassword)
        {
            String token= createJWTToken(optUser.get().getEmail(), optUser.get().getId(), optUser.get().getRoles());
            Long id= optUser.get().getId();
            List<Session>optSession= sessionRepository.findSessionDuplicateByUserId(id);
            Session session = optSession.isEmpty() ? new Session() : optSession.get(0);
//            if(optSession.get())
//            {
//                session=optSession.get();
//            }
            if (!Objects.equals(session.getClientInfo(), client_Info)) {
                session.setClientInfo(client_Info);
            }
            session.setToken(token);
            session.setClientInfo(client_Info);
            session.setUser(optUser.get());
            session.setSessionStatus(SessionStatus.ACTIVE);
            sessionRepository.save(session);
            return token;
        }
        else{
            throw new WrongPassword("Incorrect password or wrong credential");
        }
    }

    public boolean logOut(String token)
    {
       try{
           Jws<Claims> claims = Jwts.parser()
                   .verifyWith(secretKey)
                   .build()
                   .parseSignedClaims(token);
           Long id = claims.getPayload().get("userId", Long.class);
           Optional<Session> optSession=sessionRepository.findSessionByUserId(id);
           Session session= optSession.get();
           session.setSessionStatus(SessionStatus.INACTIVE);
           sessionRepository.save(session);
           return true;
       }
       catch (Exception e)
       {
           return false;
       }

    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            String email = claims.getPayload().get("email", String.class);
            Long id = claims.getPayload().get("userId", Long.class);
            Date expiration = claims.getPayload().getExpiration();
            if (expiration.before(new Date())) {
                return false;
            }
            Optional<Session>optSession= sessionRepository.findSessionByUserId(id);
            Session session= optSession.get();
            if(session.getSessionStatus()== SessionStatus.INACTIVE)
            {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private String createJWTToken(String email, Long userId, List<Role> roles){
        Map<String , Object> claims= new HashMap<>();
        claims.put("email", email);
        claims.put("userId", userId);
        claims.put("roles", roles);
        Calendar cal= Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,30);

        String token=  Jwts.builder().claims(claims).expiration(cal.getTime()).signWith(secretKey).compact();
        return token;
    }


    public boolean logOutAll(String token, String clientDevice) {
        try {
            // Parse token and get userId
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);

            Long userId = claims.getPayload().get("userId", Long.class);

            List<Session> userSessions = sessionRepository.findSessionDuplicateByUserId(userId);

            if (userSessions.isEmpty()) {
                return false;
            }

            // If you want to log out all except the current device
            for (Session session : userSessions) {
                if (session.getSessionStatus() == SessionStatus.ACTIVE) {
                    session.setSessionStatus(SessionStatus.INACTIVE);
                }
            }

            sessionRepository.saveAll(userSessions);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

}
