package com.insurance.managementsystem.repos;

import com.insurance.managementsystem.models.Policy;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
    List<Policy> findByName(String name);

    Policy findById(SingularAttribute<AbstractPersistable, Serializable> id);
}
