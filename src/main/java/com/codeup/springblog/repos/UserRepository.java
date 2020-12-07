package com.codeup.springblog.repos;

import com.codeup.springblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Add a method here: User findByUsername(String username)

}
