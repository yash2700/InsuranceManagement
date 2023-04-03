package com.insuranceManagement.InsuranceManagement.Controller;

import com.insuranceManagement.InsuranceManagement.Model.Client;
import com.insuranceManagement.InsuranceManagement.Repositories.ClientRepository;
import com.insuranceManagement.InsuranceManagement.ResponseMessage.ResponseMessage;
import com.insuranceManagement.InsuranceManagement.Service.ClientService;
import com.insuranceManagement.InsuranceManagement.ServiceImpl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
@Autowired
    ClientServiceImpl clientService;
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createClient(@RequestBody()Client client){
        return clientService.createClient(client);
    }

    @GetMapping("/clients")
    public List<Client> fetchAllClients(){
        return clientService.fecthAllClients();
    }

    @GetMapping("/client/{id}")
    public Client getClientById(@PathVariable("id")Integer id){
        return clientService.getClientByID(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteClientById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(clientService.deleteClientByID(id), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseMessage> updateClient(@PathVariable("id") Integer id,@RequestBody() Client client){
        return new ResponseEntity<>(clientService.updateClient(id,client),HttpStatus.OK);
    }

}
