package com.example.XingCheng.services;

import com.example.XingCheng.data.entities.Address;
import com.example.XingCheng.repositories.AddressRepository;
import com.vaadin.flow.component.html.Paragraph;
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

}
