package com.insuranceManagement.InsuranceManagement.Service;

import com.insuranceManagement.InsuranceManagement.Model.Client;
import com.insuranceManagement.InsuranceManagement.ResponseMessage.ResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
   ResponseEntity<ResponseMessage> createClient(Client client);

   ResponseMessage deleteClientByID(Integer id);

   List<Client> fecthAllClients();

   ResponseMessage updateClient(Integer id,Client client);

   Client getClientByID(Integer id);

}
