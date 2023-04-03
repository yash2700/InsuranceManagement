package com.insuranceManagement.InsuranceManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insuranceManagement.InsuranceManagement.Enums.ClaimStatus;
import com.insuranceManagement.InsuranceManagement.Enums.InsurancePolicyTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int claimNumber;

    private String description;

    private String claimDate;
    @Enumerated(value = EnumType.STRING)
    private ClaimStatus claimStatus;

    @OneToOne
    @JoinColumn
    @JsonIgnore
    private InsurancePolicy insurancePolicy;
}
