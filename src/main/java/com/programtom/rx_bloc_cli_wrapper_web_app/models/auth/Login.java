package com.programtom.rx_bloc_cli_wrapper_web_app.models.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Login {

    @Id
    @org.hibernate.annotations.UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "id")
    private String id;
    private String email;
    private String password;
    private boolean active;
    private String activationGuid;
    private Date activationGuidDateOfIssue;
    private String resetPasswordGuid;
    private Date resetPasswordGuidDateOfIssue;
    private boolean emailVerified;
    private int emailAttempts;
    private Date emailAttemptsDate;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
