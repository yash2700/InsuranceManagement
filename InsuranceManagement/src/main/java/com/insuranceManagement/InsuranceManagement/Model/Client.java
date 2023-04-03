package com.insuranceManagement.InsuranceManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String dateOfBirth;
    @ManyToOne
    @JoinColumn
    private Address address;

    private String contactInformation;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<InsurancePolicy> insurancePolicyList;
}
