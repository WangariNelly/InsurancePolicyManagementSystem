package com.insurance.managementsystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Policy name is required")
    private String name;

    @NotNull(message = "Premium amount is required")
    private Double premium;

    @NotNull(message = "Coverage amount is required")
    private Double coverage;

    public Policy(Long id, String name, Double premium, Double coverage) {
        this.id = id;
        this.name = name;
        this.premium = premium;
        this.coverage = coverage;
    }

    public Policy() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    public Double getCoverage() {
        return coverage;
    }

    public void setCoverage(Double coverage) {
        this.coverage = coverage;
    }


}
