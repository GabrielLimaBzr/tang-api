package com.solides.tangerino.blog.repository;

import com.solides.tangerino.blog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String login);

    User findByLoginOrEmail(String login, String email);
}
