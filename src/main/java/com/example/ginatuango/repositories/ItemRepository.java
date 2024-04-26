package com.example.ginatuango.repositories;

import com.example.ginatuango.data.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {



    @Query(value = "SELECT items.item_id, items.item_name, items.description, items.category_id, items.stock, items.price\n" +
            "FROM items\n" +
            "JOIN categories ON items.category_id = categories.category_id\n" +
            "WHERE categories.category_name = :categoryParam\n", nativeQuery = true)
    List<Item> getItemsByCategory(@Param("categoryParam") String category);
}
