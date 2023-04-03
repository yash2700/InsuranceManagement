package com.insuranceManagement.InsuranceManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String homeNo;
    private String streetName;

    private String village;
    private String city;
    private String state;
    private String country;

    @OneToMany(mappedBy = "address",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Client> clientList=new ArrayList<>();

}