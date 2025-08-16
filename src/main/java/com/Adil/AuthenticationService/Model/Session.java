package com.Adil.AuthenticationService.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Session extends BaseModel{
    private String token;
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private String clientInfo;
}
