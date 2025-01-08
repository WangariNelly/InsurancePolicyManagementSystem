package com.insurance.managementsystem.repos;

import com.insurance.managementsystem.models.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    List<Policy> findByName(String name);
}
