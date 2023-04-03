package com.insuranceManagement.InsuranceManagement.ServiceImpl;

import com.insuranceManagement.InsuranceManagement.EntryDtos.ClaimDto;
import com.insuranceManagement.InsuranceManagement.Enums.ClaimStatus;
import com.insuranceManagement.InsuranceManagement.Exceptions.DateException;
import com.insuranceManagement.InsuranceManagement.Exceptions.IdNotFoundException;
import com.insuranceManagement.InsuranceManagement.Exceptions.InvalidClaimDescriptionException;
import com.insuranceManagement.InsuranceManagement.Exceptions.InvalidClaimNumberException;
import com.insuranceManagement.InsuranceManagement.Model.Claim;
import com.insuranceManagement.InsuranceManagement.Model.InsurancePolicy;
import com.insuranceManagement.InsuranceManagement.Repositories.ClaimRepository;
import com.insuranceManagement.InsuranceManagement.Repositories.InsurancePolicyRepository;
import com.insuranceManagement.InsuranceManagement.ResponseMessage.ResponseMessage;
import com.insuranceManagement.InsuranceManagement.Service.ClaimService;
import com.insuranceManagement.InsuranceManagement.UpdateDtos.ClaimUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClaimServiceImpl implements ClaimService {
    @Autowired
    ClaimRepository claimRepository;
    @Autowired
    InsurancePolicyRepository insurancePolicyRepository;


    @Override
    public ResponseMessage createClaim(ClaimDto claimDto) {
        validateClaim(claimDto);
        InsurancePolicy insurancePolicy=insurancePolicyRepository.findById(claimDto.getInsurancePolicyId()).get();
        Claim claim=Claim.builder()
                .claimNumber(claimDto.getClaimNumber())
                .claimDate(claimDto.getClaimDate())
                .claimStatus(ClaimStatus.PENDING)
                .description(claimDto.getDescription())
                .insurancePolicy(insurancePolicy)
                .build();
        insurancePolicy.setClaim(claim);
        insurancePolicyRepository.save(insurancePolicy);
        return new ResponseMessage(HttpStatus.CREATED,"claim has been created");
    }

    @Override
    public ClaimDto getClaimById(Integer id) {
        if(!claimRepository.existsById(id))
            throw new IdNotFoundException("Service.ID_NOT_FOUND");
        Claim claim=claimRepository.findById(id).get();
        ClaimDto claimDto=ClaimDto.builder()
                .claimNumber(claim.getClaimNumber())
                .claimDate(claim.getClaimDate())
                .description(claim.getDescription())
                .claimStatus(claim.getClaimStatus())
                .insurancePolicyId(claim.getInsurancePolicy().getId())
                .build();
        return claimDto;
    }

    @Override
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    @Override
    public ResponseMessage deleteClaimByID(Integer id) {
        if(!claimRepository.existsById(id))
            throw new IdNotFoundException("Service.ID_NOT_FOUND");
    Claim claim=claimRepository.findById(id).get();
    Optional<InsurancePolicy> insurancePolicy=insurancePolicyRepository.findById(claim.getInsurancePolicy().getId());
    insurancePolicy.get().setClaim(null);
        claimRepository.deleteById(id);
        return new ResponseMessage(HttpStatus.GONE,"claim successfully deleted");

    }

    @Override
    public ResponseMessage updateClaim(Integer id, ClaimUpdateDto claimDto) {
        if(!claimRepository.existsById(id))
            throw new IdNotFoundException("Service.ID_NOT_FOUND");
        if(!insurancePolicyRepository.existsById(claimRepository.findById(id).get().getInsurancePolicy().getId())){
            throw new IdNotFoundException("Service.ID_NOT_FOUND");

        }

        if(!validateClaimDescription(claimDto.getDescription())){
            throw new InvalidClaimDescriptionException("RegistrationService.INVALID_CLAIM_DESCRIPTION\n");
        }
        if(!validateClaimDate(claimDto.getClaimDate())){
            throw new DateException("RegistrationService.INVALID_DATE");
        }
        Claim claim=claimRepository.findById(id).get();
        claim.setClaimDate(claimDto.getClaimDate());
        claim.setClaimStatus(claimDto.getClaimStatus());
        claim.setDescription(claimDto.getDescription());
        claimRepository.save(claim);
        return new ResponseMessage(HttpStatus.OK,"claim updated successfully");
    }

    private void validateClaim(ClaimDto claimDto) {
        if(!validateClaimNumer(claimDto.getClaimNumber())){
            throw new InvalidClaimNumberException("RegistrationService.INVALID_CLAIM_NUMBER");
        }
        if(!validateClaimDescription(claimDto.getDescription())){
            throw new InvalidClaimDescriptionException("RegistrationService.INVALID_CLAIM_DESCRIPTION\n");
        }
        if(!validateClaimDate(claimDto.getClaimDate())){
            throw new DateException("RegistrationService.INVALID_DATE");
        }
        if(!valdateClaimInsuracePolicyId(claimDto.getInsurancePolicyId())){
            throw new IdNotFoundException("Service.ID_NOT_FOUND");
        }


    }

    private boolean valdateClaimInsuracePolicyId(int insurancePolicyId) {
        if(!insurancePolicyRepository.existsById(insurancePolicyId) || claimRepository.existsByInsurancePolicyId(insurancePolicyId))
            return false;
        return true;
    }

    private boolean validateClaimDate(String claimDate) {
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
            dateTimeFormatter.parse(claimDate.toString());
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    private boolean validateClaimDescription(String description) {
        if(description==null || description.length()==0 || description.equals(""))
                return false;
        return true;
    }

    private boolean validateClaimNumer(int claimNumber) {
        String regex = "^[0-9]*$";
        Pattern pattern2=Pattern.compile(regex);
        Matcher matcher2=pattern2.matcher(String.valueOf(claimNumber));
        if(matcher2.matches()){
            return true;
        }
        return false;
    }


}
