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


    public void addCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public CartItem getCartItemByCartAndItemAndType(int cartId, int itemId, int typeId){
        return cartItemRepository.getCartItemByCartAndItemAndType(cartId, itemId, typeId);
    }

    public void updateCartItem(CartItem cartItem){
        cartItemRepository.save(cartItem);
    }
}
