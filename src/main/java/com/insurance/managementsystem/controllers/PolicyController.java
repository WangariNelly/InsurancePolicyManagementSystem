package com.insurance.managementsystem.controllers;


import com.insurance.managementsystem.models.Policy;
import com.insurance.managementsystem.services.AuthService;
import com.insurance.managementsystem.services.PolicyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;
    private AuthService authService;

    @GetMapping
    @Secured("ROLE_USER")
    public List<Policy> getAllPolicies(){
        return policyService.getAllPolicies();
    }

    @PostMapping("/new")
    @Secured("ROLE_USER")
    public ResponseEntity<Policy> addPolicy(@Valid @RequestBody Policy policy){
        return ResponseEntity.ok(policyService.addPolicy(policy));
    }

    @PutMapping("/{id}")
    @Secured("ROLE_USER")
    public ResponseEntity<Policy> updatePolicy(@PathVariable Long id, @Valid @RequestBody Policy policy){
        return ResponseEntity.ok(policyService.updatePolicy(id, policy));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deletePolicy(@PathVariable Long id){
        policyService.deletePolicy(id);
        return ResponseEntity.noContent().build();
    }
}
