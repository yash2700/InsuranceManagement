package com.insuranceManagement.InsuranceManagement.ServiceImpl;

import com.insuranceManagement.InsuranceManagement.Exceptions.*;
import com.insuranceManagement.InsuranceManagement.Model.Address;
import com.insuranceManagement.InsuranceManagement.Model.Client;
import com.insuranceManagement.InsuranceManagement.Repositories.AddressRepository;
import com.insuranceManagement.InsuranceManagement.Repositories.ClaimRepository;
import com.insuranceManagement.InsuranceManagement.Repositories.ClientRepository;
import com.insuranceManagement.InsuranceManagement.ResponseMessage.ResponseMessage;
import com.insuranceManagement.InsuranceManagement.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    private ClaimRepository claimRepository;

    @Override
    public ResponseEntity<ResponseMessage> createClient(Client client) {
        validate(client);
        client.getAddress().getClientList().add(client);
        addressRepository.save(client.getAddress());
        clientRepository.save(client);
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.CREATED,"client created"),HttpStatus.CREATED);
    }
    public void validate(Client client){
        if(client.getName()==null || client.getAddress()==null ||client.getContactInformation()==null || client.getDateOfBirth()==null)
            throw new NullClientException("RegistrationService.NULL_CLIENT");
        if(clientRepository.existsByNameAndContactInformation(client.getName(),client.getContactInformation())) {
            throw new ClientAlreadyExistsException("RegistrationService.CLIENT_EXISTS");
        }
        if(!validateName(client.getName())){
            throw new ClientNameException("RegistrationService.INVALID_NAME");
        }
        if(!validateDateOfBirth(client.getDateOfBirth())){
            throw new DateException("RegistrationService.INVALID_DATE");
        }
        if(!validateContactInformation(client.getContactInformation())){
            throw new ContactInformationException("RegistrationService.INVALID_CONTACT_INFO");
        }
        if(!validateAddress(client.getAddress())){
            throw new AddressException("RegistrationService.INVALID_ADDRESS");
        }
    }

    private boolean validateAddress(Address address) {
//
        if(address.getStreetName()==null|| address.getStreetName().equals(""))
            return false;

        if(address.getVillage()==null || address.getVillage().equals(""))
            return false;

        if(address.getCity().equals("") || address.getCity()==null)
            return false;

        if(address.getState().equals("") || address.getState()==null)
            return false;

        if(address.getCountry().equals("") || address.getCountry()==null)
            return false;

        return true;

    }

    private boolean validateContactInformation(String contactInformation) {
        String regex = "^\\d{10}$";
        Pattern pattern2=Pattern.compile(regex);
        Matcher matcher2=pattern2.matcher(contactInformation);
        if(matcher2.matches()){
            return true;
        }
        return false;
    }

    public boolean validateName(String name){
        if(name.equals("") || name==null)
            return false;
        String regex="^[a-zA-Z0-9 ]{4,15}+$";
        Pattern pattern1=Pattern.compile(regex);
        Matcher matcher1=pattern1.matcher(name);
        if(matcher1.matches()){
            return true;
        }
        return false;
    }
    public boolean validateDateOfBirth(String date){
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
            dateTimeFormatter.parse(date);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public List<Client> fecthAllClients(){
        return clientRepository.findAll();
    }
        public Client getClientByID(Integer id){
        if(!clientRepository.existsById(id)){
            throw new IdNotFoundException("Service.ID_NOT_FOUND");
        }
        return  clientRepository.findById(id).get();
    }

    public ResponseMessage deleteClientByID(Integer id){
        if(!clientRepository.existsById(id)){
            throw new IdNotFoundException("Service.ID_NOT_FOUND");
        }else {
            addressRepository.delete(clientRepository.findById(id).get().getAddress());
            clientRepository.deleteById(id);
            return new ResponseMessage(HttpStatus.GONE,"Successfully Deleted client with id : "+String.valueOf(id));
        }
    }

    public ResponseMessage updateClient(Integer id,Client client){
        if(!clientRepository.existsById(id))
                throw new IdNotFoundException("Service.ID_NOT_FOUND");
        else{
            validate(client);
            Client client1=clientRepository.findById(id).get();
            client1.setAddress(client.getAddress());
            client1.setName(client.getName());
            client1.setContactInformation(client.getContactInformation());
            client1.setDateOfBirth(client.getDateOfBirth());
            client1.setInsurancePolicyList(client.getInsurancePolicyList());
            addressRepository.save(client1.getAddress());
            clientRepository.save(client1);
        }
        return new ResponseMessage(HttpStatus.ACCEPTED,"updation successful");
    }


}
