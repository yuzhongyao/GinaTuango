package com.example.ginatuango.repositories;

import com.example.ginatuango.data.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

    @Query(value = "SELECT * FROM cartItems AS ci WHERE " +
            "ci.cart_id = :cartId;",nativeQuery = true)
    CartItem getCartItemsByCart(@Param("cartId") int cartId);
}
