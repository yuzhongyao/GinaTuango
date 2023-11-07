package com.example.ginatuango.services;

import com.example.ginatuango.data.entities.Order;
import com.example.ginatuango.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public Optional<Order> getOrder(int id){
        return orderRepository.findById(id);
    }


}
