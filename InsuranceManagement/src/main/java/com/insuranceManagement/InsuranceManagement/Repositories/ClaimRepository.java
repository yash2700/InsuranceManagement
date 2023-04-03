package com.insuranceManagement.InsuranceManagement.Repositories;

import com.insuranceManagement.InsuranceManagement.Model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim,Integer> {

    boolean existsByInsurancePolicyId(int insurancePolicyId);
}
