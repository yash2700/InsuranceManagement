package com.insuranceManagement.InsuranceManagement.Repositories;

import com.insuranceManagement.InsuranceManagement.Model.InsurancePolicy;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer> {
}
