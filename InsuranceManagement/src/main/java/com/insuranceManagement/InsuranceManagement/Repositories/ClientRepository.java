package com.insuranceManagement.InsuranceManagement.Repositories;

import com.insuranceManagement.InsuranceManagement.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {


    boolean existsByNameAndContactInformation(String name, String contactInformation);
}
