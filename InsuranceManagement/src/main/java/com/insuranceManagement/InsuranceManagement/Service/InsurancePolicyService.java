package com.insuranceManagement.InsuranceManagement.Service;

import com.insuranceManagement.InsuranceManagement.EntryDtos.InsurancePolicyDto;
import com.insuranceManagement.InsuranceManagement.Model.InsurancePolicy;
import com.insuranceManagement.InsuranceManagement.ResponseMessage.ResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InsurancePolicyService {
    ResponseMessage createInsurancePolicy(InsurancePolicyDto insurancePolicy);

    List<InsurancePolicy> fetchAllInsurancePolicies();

    InsurancePolicyDto getInsurancePolicyById(Integer id);

    ResponseMessage updateInsurancePolicy(Integer id,InsurancePolicyDto insurancePolicy);

    ResponseMessage deleteinsuancePolicyById(Integer id);

}
