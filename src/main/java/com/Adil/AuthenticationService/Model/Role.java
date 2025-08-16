package com.Adil.AuthenticationService.Model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cache.annotation.EnableCaching;

@Getter
@Setter
@Entity
public class Role extends BaseModel{
    private String role;
}
