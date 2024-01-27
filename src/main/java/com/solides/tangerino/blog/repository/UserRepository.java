package com.solides.tangerino.blog.repository;

import com.solides.tangerino.blog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
