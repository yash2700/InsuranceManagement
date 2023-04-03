package com.insuranceManagement.InsuranceManagement.EntryDtos;

import com.insuranceManagement.InsuranceManagement.Enums.ClaimStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimDto {
    private int claimNumber;

    private String description;

    private String claimDate;
    @Enumerated(value = EnumType.STRING)
    private ClaimStatus claimStatus;
    private int insurancePolicyId;
}
