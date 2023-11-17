package com.example.ginatuango.services;


import com.example.ginatuango.repositories.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


@Service
public class CustomService {

    private final CustomRepository customRepository;

    @Autowired
    public CustomService(CustomRepository repository){
        this.customRepository = repository;
    }

    public List<Object[]> getTodaysOrders(){
//        return customRepository.getTodaysOrders(LocalDate.of(2023,9,2));
        return customRepository.getTodaysOrders(LocalDate.now(ZoneId.of("America/New_York")));
    }

    public List<Object[]> getOrders(LocalDate date) {
        return customRepository.getTodaysOrders(date);

    }
}
