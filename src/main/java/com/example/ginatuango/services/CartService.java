package com.example.ginatuango.services;

import com.example.ginatuango.data.entities.Cart;
import com.example.ginatuango.data.entities.User;
import com.example.ginatuango.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getCartByUser(Integer user){
        return cartRepository.getCartByUser(user);
    }

}
