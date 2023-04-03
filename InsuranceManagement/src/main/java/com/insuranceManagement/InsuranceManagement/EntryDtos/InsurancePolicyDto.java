package com.insuranceManagement.InsuranceManagement.EntryDtos;

import com.insuranceManagement.InsuranceManagement.Enums.InsurancePolicyTypes;
import com.insuranceManagement.InsuranceManagement.Model.InsurancePolicy;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsurancePolicyDto {
    private int policyNumber;
    @Enumerated(value = EnumType.STRING)
    private InsurancePolicyTypes insuranceType;

    private double coverageAmount;

    private double premium;

    private String startDate;

    private String endDate;
    private int clientId;
}
