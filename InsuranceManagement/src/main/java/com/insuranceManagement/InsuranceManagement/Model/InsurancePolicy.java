package com.insuranceManagement.InsuranceManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.insuranceManagement.InsuranceManagement.Enums.InsurancePolicyTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsurancePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private int policyNumber;
    @Enumerated(value = EnumType.STRING)
    private InsurancePolicyTypes insuranceType;

    private double coverageAmount;

    private double premium;

    private String startDate;

    private String endDate;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Client client;

    @OneToOne(mappedBy = "insurancePolicy",cascade = CascadeType.ALL)
    private Claim claim;

}
