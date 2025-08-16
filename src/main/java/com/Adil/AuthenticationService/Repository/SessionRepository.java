package com.Adil.AuthenticationService.Repository;

import com.Adil.AuthenticationService.Model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

    public Optional<Session> findSessionById(Long id);
    public Optional<Session>findSessionByUserId(Long userId);
//    public  Session findSessionById(Long userId);
//
   public List<Session> findSessionDuplicateByUserId(Long id);
}
