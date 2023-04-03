package com.insuranceManagement.InsuranceManagement.UpdateDtos;

import com.insuranceManagement.InsuranceManagement.Enums.ClaimStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimUpdateDto {
    private String description;

    private String claimDate;
    @Enumerated(value = EnumType.STRING)
    private ClaimStatus claimStatus;
}
