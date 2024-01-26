package com.solides.tangerino.blog.model.entity;

import com.solides.tangerino.blog.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "TB_USERS")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    @Column(name = "DT_CREATED")
    private LocalDateTime created;

    @Column(name = "INT_ROLE", nullable = false)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Autorizacao simples não ha necessidade de verificar outras roles
        return List.of(new SimpleGrantedAuthority("ROLE_DEFAULT"));
    }

    @Override
    public String getPassword() {
        return passphrase;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
