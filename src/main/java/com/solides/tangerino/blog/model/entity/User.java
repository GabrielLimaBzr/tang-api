package com.solides.tangerino.blog.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USER")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue
    @Column(name = "INT_ID_USER", nullable = false)
    private Long id;

    @Column(name = "STR_LOGIN", nullable = false, unique = true, length = 100)
    private String login;

    @Column(name = "STR_NAME", nullable = false, length = 100)
    private String name;

    @Column(name ="STR_EMAIL", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "STR_PASSPHRASE", nullable = false)
    private String passphrase;

    @CreatedDate
    @Column(name = "DT")
    private LocalDateTime created;


}
