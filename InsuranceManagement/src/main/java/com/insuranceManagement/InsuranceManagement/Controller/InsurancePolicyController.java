package com.insuranceManagement.InsuranceManagement.Controller;

import com.insuranceManagement.InsuranceManagement.EntryDtos.InsurancePolicyDto;
import com.insuranceManagement.InsuranceManagement.Model.InsurancePolicy;
import com.insuranceManagement.InsuranceManagement.ResponseMessage.ResponseMessage;
import com.insuranceManagement.InsuranceManagement.ServiceImpl.InsurancePolicyServiceImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policy")
public class InsurancePolicyController {

    @Autowired
    InsurancePolicyServiceImpl insurancePolicyService;

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createInsurancePolicy(@RequestBody() InsurancePolicyDto insurancePolicy){
        return new ResponseEntity<>(insurancePolicyService.createInsurancePolicy(insurancePolicy), HttpStatus.CREATED);
    }

    @GetMapping("/policies")
    public List<InsurancePolicy> fetchAllPolicies(){
        return insurancePolicyService.fetchAllInsurancePolicies();
    }

    @GetMapping("/getById/{id}")
    public InsurancePolicyDto getById(@PathVariable("id")Integer id){
        return insurancePolicyService.getInsurancePolicyById(id);
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<ResponseMessage> updateById(@PathVariable("id")Integer id,@RequestBody() InsurancePolicyDto insurancePolicyDto){
        return new ResponseEntity<>(insurancePolicyService.updateInsurancePolicy(id,insurancePolicyDto),HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ResponseMessage> deleteById(@PathVariable("id")Integer id){
        return new ResponseEntity<>(insurancePolicyService.deleteinsuancePolicyById(id),HttpStatus.GONE);
    }
}
