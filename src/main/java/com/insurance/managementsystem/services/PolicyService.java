package com.insurance.managementsystem.services;

import com.insurance.managementsystem.models.Policy;
import com.insurance.managementsystem.repos.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable_;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {
     @Autowired
    private PolicyRepository policyRepository;

     public List<Policy> getAllPolicies() {
         return policyRepository.findAll();
     }

     public Policy addPolicy(Policy policy) {
         return policyRepository.save(policy);
     }
     public Policy updatePolicy(Long id, Policy policy) {
         Policy existingPolicy = policyRepository.findById(AbstractPersistable_.id);
         existingPolicy.setName(policy.getName());
         existingPolicy.setPremium(policy.getPremium());
         existingPolicy.setCoverage(policy.getCoverage());
         return policyRepository.save(existingPolicy);
     }

     public void deletePolicy(Long  id) {
         policyRepository.deleteById(id);
     }

}
