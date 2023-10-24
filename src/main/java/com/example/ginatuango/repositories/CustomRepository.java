package com.example.ginatuango.repositories;


import com.example.ginatuango.data.entities.Custom;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


//JPA needs to associate with entity class
//bypassed with dummy Custom class
@Repository
public interface CustomRepository extends JpaRepository<Custom,Integer>{

    //
    @Query(value = "SELECT\n" +
            "    i.item_id,\n" +
            "    i.item_name,\n" +
            "    SUM(ii.quantity) AS total_quantity_sold,\n" +
            "    ist.type_name AS sale_type,\n" +
            "\tii.isOnSale AS was_on_sale\n" +
            "FROM\n" +
            "    items AS i\n" +
            "JOIN\n" +
            "    itemSales AS ii ON i.item_id = ii.item_id\n" +
            "JOIN\n" +
            "    itemSaleTypes AS ist ON ii.type_id = ist.type_id\n" +
            "WHERE\n" +
            "    ii.sale_date = :dateParam\n" +
            "GROUP BY\n" +
            "    i.item_id, i.item_name, ist.type_name, ii.isOnSale;", nativeQuery = true)
    List<Object[]> getTodaysOrders(@Param("dateParam")LocalDate date);

}

