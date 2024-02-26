package com.example.ginatuango.repositories;

import com.example.ginatuango.data.entities.Cart;
import com.example.ginatuango.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {

    @Query(value = "SELECT * " +
            "FROM shoppingCarts AS sc WHERE sc.user_id = :userParam", nativeQuery = true)
    Cart getCartByUser(@Param("userParam") Integer user_id);


}
