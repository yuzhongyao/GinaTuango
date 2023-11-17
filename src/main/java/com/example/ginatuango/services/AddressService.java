package com.example.ginatuango.services;

import com.example.ginatuango.data.entities.Address;
import com.example.ginatuango.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public List<Address> getAddresses(){
        return addressRepository.findAll();
    }

    public void insertNewAddress(Address address){
        addressRepository.save(address);
    }
}
