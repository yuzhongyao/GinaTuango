package com.example.ginatuango.services;

import com.example.ginatuango.data.entities.CartItem;
import com.example.ginatuango.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository){
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getCartItemsByCart(int cartId){
        return  cartItemRepository.getCartItemsByCart(cartId);
    }



}
