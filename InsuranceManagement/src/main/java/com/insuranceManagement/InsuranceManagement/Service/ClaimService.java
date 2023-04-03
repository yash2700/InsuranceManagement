package com.insuranceManagement.InsuranceManagement.Service;

import com.insuranceManagement.InsuranceManagement.EntryDtos.ClaimDto;
import com.insuranceManagement.InsuranceManagement.Model.Claim;
import com.insuranceManagement.InsuranceManagement.ResponseMessage.ResponseMessage;
import com.insuranceManagement.InsuranceManagement.UpdateDtos.ClaimUpdateDto;

import java.util.List;

public interface ClaimService {
    ResponseMessage createClaim(ClaimDto claimDto);
    ClaimDto getClaimById(Integer id);

    List<Claim> getAllClaims();

    ResponseMessage deleteClaimByID(Integer id);

    ResponseMessage updateClaim(Integer id, ClaimUpdateDto claimDto);
}
