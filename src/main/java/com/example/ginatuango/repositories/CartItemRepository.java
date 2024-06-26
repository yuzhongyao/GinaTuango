package com.example.ginatuango.repositories;

import com.example.ginatuango.data.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

    @Query(value = "SELECT * FROM cartItems AS " +
            "ci WHERE ci.cart_id = :cartId"
            ,nativeQuery = true)
    List<CartItem> getCartItemsByCart(@Param("cartId") int cartId);

    @Query(value = "SELECT * FROM cartItems as ci " +
            "WHERE ci.cart_id =:cartId AND ci.item_id =:itemId " +
            "AND ci.type_id =:typeId" +
            "",nativeQuery = true)
    CartItem getCartItemByCartAndItemAndType(@Param("cartId") int cartId, @Param("itemId") int itemId, @Param("typeId") int typeId);

}
