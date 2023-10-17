package com.example.XingCheng.services;

import com.example.XingCheng.data.entities.Order;
import com.example.XingCheng.data.entities.User;
import com.example.XingCheng.repositories.OrderRepository;
import com.example.XingCheng.repositories.UserRepository;
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
