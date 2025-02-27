package com.insurance.managementsystem.repos;

import com.insurance.managementsystem.models.Role;
import com.insurance.managementsystem.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

