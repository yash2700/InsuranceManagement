package com.insuranceManagement.InsuranceManagement.Repositories;

import com.insuranceManagement.InsuranceManagement.Model.Address;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
