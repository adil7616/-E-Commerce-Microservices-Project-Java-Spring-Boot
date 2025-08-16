package com.Adil.AuthenticationService.Controller;

import com.Adil.AuthenticationService.DTOs.*;
import com.Adil.AuthenticationService.DTOs.ResponseStatus;
import com.Adil.AuthenticationService.Repository.AuthenticationRepository;
import com.Adil.AuthenticationService.Service.AuthenticationService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.websocket.server.PathParam;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final AuthenticationService authenticationService;
//    private final AuthenticationRepository authenticationRepository;

    public UserController(AuthenticationService authenticationService,
                          AuthenticationRepository authenticationRepository){
        this.authenticationService=authenticationService;
//        this.authenticationRepository = authenticationRepository;
    }
    @PostMapping("/signUp")
    public UserSignUpResponseDTO signUp(@RequestBody UserSignUpRequestDTO request)
    {
        UserSignUpResponseDTO response= new UserSignUpResponseDTO();
        boolean isRegistered= authenticationService.registerUser(request.getEmail(),request.getPassword());
        if(isRegistered)
        {
            response.setResponseStatus(ResponseStatus.SUCCESS);
        }
        else {
            response.setResponseStatus(ResponseStatus.FAILED);
        }
        return response;
    }

    @PostMapping("/signIn")
    public ResponseEntity<UserSignInResponseDTO> signIn(@RequestBody UserSignInRequestDTO request,HttpServletRequest client)
    {
        UserSignInResponseDTO response= new UserSignInResponseDTO();
        try{
            String clientInfo=client.getHeader("User-Agent");
            String token= authenticationService.loginUser(request.getEmail(),request.getPassword(),clientInfo);
            response.setToken(token);
            MultiValueMap<String, String> header= new LinkedMultiValueMap<>();
            header.add("AUTH_TOKEN",token);
            ResponseEntity<UserSignInResponseDTO> responseEntity=new ResponseEntity<>(response,header, HttpStatus.OK);
            response.setResponseStatus(ResponseStatus.SUCCESS);
            return responseEntity;
        }
        catch (Exception e)
        {
            response.setResponseStatus(ResponseStatus.FAILED);
            throw e;
        }
    }

    @GetMapping("/validate")
    public boolean validateUser(@RequestParam("ticket") String token)
    {
//        String userInfo= request.getHeader("User_Agent");
        return authenticationService.validateToken(token);
    }

    @GetMapping("/userLogOut")
    public boolean logOut(HttpServletRequest request )
    {
        String token= request.getHeader("AUTH_TOKEN");
        authenticationService.logOut(token);
        return authenticationService.logOut(token);
    }

    @GetMapping("/logOutAll")
    public boolean logOutAll(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        String token= request.getHeader("AUTH_TOKEN");
        return authenticationService.logOutAll(token, userAgent);
    }



}
