package com.example.ginatuango.repositories;

import com.example.ginatuango.data.entities.ItemSale;
import com.example.ginatuango.data.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemSaleRepository extends JpaRepository<ItemSale, Integer> {

    public List<ItemSale> findByOrder(Order order);

    @Query(value = "SELECT * FROM itemSales WHERE item_id = :itemParam ;", nativeQuery = true)
    List<ItemSale> getItemSalesByItemId(@Param("itemParam")int id);

}
