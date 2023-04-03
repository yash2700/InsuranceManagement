package com.insuranceManagement.InsuranceManagement.ServiceImpl;

import com.insuranceManagement.InsuranceManagement.EntryDtos.InsurancePolicyDto;
import com.insuranceManagement.InsuranceManagement.Enums.InsurancePolicyTypes;
import com.insuranceManagement.InsuranceManagement.Exceptions.*;
import com.insuranceManagement.InsuranceManagement.Model.Client;
import com.insuranceManagement.InsuranceManagement.Model.InsurancePolicy;
import com.insuranceManagement.InsuranceManagement.Repositories.ClientRepository;
import com.insuranceManagement.InsuranceManagement.Repositories.InsurancePolicyRepository;
import com.insuranceManagement.InsuranceManagement.ResponseMessage.ResponseMessage;
import com.insuranceManagement.InsuranceManagement.Service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    InsurancePolicyRepository insurancePolicyRepository;


    @Override
    public ResponseMessage createInsurancePolicy(InsurancePolicyDto insurancePolicyDto) {
        validate(insurancePolicyDto);
        InsurancePolicy insurancePolicy=InsurancePolicy.builder()
                .policyNumber(insurancePolicyDto.getPolicyNumber())
                .insuranceType(insurancePolicyDto.getInsuranceType())
                .coverageAmount(insurancePolicyDto.getCoverageAmount())
                .premium(insurancePolicyDto.getPremium())
                .startDate(insurancePolicyDto.getStartDate())
                .endDate(insurancePolicyDto.getEndDate())
                .build();
        insurancePolicy.setClient(clientRepository.findById(insurancePolicyDto.getClientId()).get());
        Client client=clientRepository.findById(insurancePolicyDto.getClientId()).get();
        client.getInsurancePolicyList().add(insurancePolicy);
        insurancePolicyRepository.save(insurancePolicy);
        return new ResponseMessage(HttpStatus.CREATED,"Insurance Policy Created");
    }
    public void validate(InsurancePolicyDto insurancePolicy){
        if(!validatePolicyNumer(insurancePolicy.getPolicyNumber())){
            throw new InsurancePolicyNumberException("RegistrationService.INVALID_NUMBER");
        }
        if(!validatePolicyType(insurancePolicy.getInsuranceType())){
            throw new InvalidPolicyTypeException("RegistrationService.INVALID_POLICY_TYPE");
        }
        if(!validateAmount(insurancePolicy.getCoverageAmount())){
            throw new InvalidInsuranceAmountException("RegistrationService.INVALID_POLICY_AMOUNT");
        }
        if(!validateAmount(insurancePolicy.getPremium())){
            throw new InvalidInsuranceAmountException("RegistrationService.INVALID_POLICY_AMOUNT");
        }
        if(!validateDate(insurancePolicy.getStartDate())){
            throw new DateException("RegistrationService.INVALID_DATE");
        }
        if(!validateDate(insurancePolicy.getEndDate())){
            throw new DateException("RegistrationService.INVALID_DATE");
        }
        if(!validateClientId(insurancePolicy.getClientId())){
            throw new IdNotFoundException("Service.ID_NOT_FOUND");
        }
    }

    private boolean validateClientId(int clientId) {
        if(!clientRepository.existsById(clientId)){
            return false;
        }
        System.out.println(clientId);
        return true;

    }

    private boolean validateDate(String startDate) {
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
            dateTimeFormatter.parse(String.valueOf(startDate));
        }
        catch (Exception e){
            return false;
        }
        System.out.println(startDate);

        return true;

    }

    private boolean validateAmount(double Amount) {
        if(Amount==0.0){
            return false;
        }
        System.out.println(Amount);

        return true;
    }

    private boolean validatePolicyType(InsurancePolicyTypes insuranceType) {
        boolean b=false;
        for(InsurancePolicyTypes types:InsurancePolicyTypes.values()){
            if(types.equals(insuranceType)){
                b=true;
                break;
            }
        }
        System.out.println(insuranceType);
        return b;
    }

    private boolean validatePolicyNumer(int policyNumber) {
        String regex="^[0-9]*$";
        Pattern pattern1=Pattern.compile(regex);
        Matcher matcher1=pattern1.matcher(String.valueOf(policyNumber));
        if(!matcher1.matches())
            return false;
        System.out.println(policyNumber);

        return true;
    }

    @Override
    public List<InsurancePolicy> fetchAllInsurancePolicies() {
        return insurancePolicyRepository.findAll();
    }

    @Override
    public InsurancePolicyDto getInsurancePolicyById(Integer id) {
        if(!insurancePolicyRepository.existsById(id))
                throw new IdNotFoundException("Service.ID_NOT_FOUND");
        InsurancePolicy insurancePolicy=insurancePolicyRepository.findById(id).get();
        InsurancePolicyDto insurancePolicyDto=InsurancePolicyDto.builder()
                .policyNumber(insurancePolicy.getPolicyNumber())
                .clientId(insurancePolicy.getClient().getId())
                .insuranceType(insurancePolicy.getInsuranceType())
                .coverageAmount(insurancePolicy.getCoverageAmount())
                .premium(insurancePolicy.getPremium())
                .startDate(insurancePolicy.getStartDate())
                .endDate(insurancePolicy.getEndDate())
                .build();
        return insurancePolicyDto;

    }

    @Override
    public ResponseMessage updateInsurancePolicy(Integer id,InsurancePolicyDto insurancePolicy) {
        if(!insurancePolicyRepository.existsById(id))
            throw new IdNotFoundException("Service.ID_NOT_FOUND");
        validate(insurancePolicy);
        InsurancePolicy insurancePolicy1=insurancePolicyRepository.findById(id).get();
        if(insurancePolicy.getClientId()!=insurancePolicy1.getClient().getId()){
            return new ResponseMessage(HttpStatus.CONFLICT,"please enter the correct client id");
        }
        if(insurancePolicy.getPolicyNumber()!=insurancePolicy1.getPolicyNumber()){
            return new ResponseMessage(HttpStatus.CONFLICT,"please enter the correct policy Number");
        }

            insurancePolicy1=insurancePolicyRepository.findById(id).get();
        insurancePolicy1.setInsuranceType(insurancePolicy.getInsuranceType());
        insurancePolicy1.setPolicyNumber(insurancePolicy.getPolicyNumber());
        insurancePolicy1.setPremium(insurancePolicy.getPremium());
        insurancePolicy1.setCoverageAmount(insurancePolicy.getCoverageAmount());
        insurancePolicy1.setStartDate(insurancePolicy.getStartDate());
        insurancePolicy1.setEndDate(insurancePolicy.getEndDate());
        Client client=clientRepository.findById(insurancePolicy.getClientId()).get();
        insurancePolicyRepository.save(insurancePolicy1);
        return new ResponseMessage(HttpStatus.OK,"Successfully updated");

    }

    @Override
    public ResponseMessage deleteinsuancePolicyById(Integer id) {

        if(!insurancePolicyRepository.existsById(id))
            throw new IdNotFoundException("Service.ID_NOT_FOUND");
        int clientId=insurancePolicyRepository.findById(id).get().getClient().getId();
        Client client=clientRepository.findById(clientId).get();
        client.getInsurancePolicyList().remove(insurancePolicyRepository.findById(id).get());
        clientRepository.save(client);
        insurancePolicyRepository.deleteById(id);
        return new ResponseMessage(HttpStatus.GONE,"Deletion Successful");

    }
}
