package com.Adil.AuthenticationService.Repository;

import com.Adil.AuthenticationService.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
