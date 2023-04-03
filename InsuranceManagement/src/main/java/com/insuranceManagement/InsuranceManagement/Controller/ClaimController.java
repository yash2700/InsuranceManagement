package com.insuranceManagement.InsuranceManagement.Controller;

import com.insuranceManagement.InsuranceManagement.EntryDtos.ClaimDto;
import com.insuranceManagement.InsuranceManagement.Model.Claim;
import com.insuranceManagement.InsuranceManagement.Model.Client;
import com.insuranceManagement.InsuranceManagement.Repositories.ClaimRepository;
import com.insuranceManagement.InsuranceManagement.ResponseMessage.ResponseMessage;
import com.insuranceManagement.InsuranceManagement.ServiceImpl.ClaimServiceImpl;
import com.insuranceManagement.InsuranceManagement.UpdateDtos.ClaimUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/claim")
public class ClaimController {
    @Autowired
    ClaimServiceImpl claimService;
    @Autowired
    private ClaimRepository claimRepository;

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createClaim(@RequestBody()ClaimDto claimDto){
        return new ResponseEntity<>(claimService.createClaim(claimDto), HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ClaimDto> getClaimById(@PathVariable("id")Integer id){
        return new ResponseEntity<>(claimService.getClaimById(id),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Claim>> getAllClaims(){
        return new ResponseEntity<>(claimService.getAllClaims(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ResponseMessage> deleteClaimById(@PathVariable("id")Integer id){
            return new ResponseEntity<>(claimService.deleteClaimByID(id),HttpStatus.GONE);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseMessage> updateClaim(@PathVariable("id")Integer id,@RequestBody() ClaimUpdateDto claimDto){
        return new ResponseEntity<>(claimService.updateClaim(id,claimDto),HttpStatus.OK);
    }


}
