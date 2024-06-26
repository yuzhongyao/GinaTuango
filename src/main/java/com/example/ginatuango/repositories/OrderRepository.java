package com.example.ginatuango.repositories;

import com.example.ginatuango.data.entities.Order;
import com.example.ginatuango.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT \n" +
            "    u.user_name,\n" +
            "    i.item_name AS item,\n" +
            "    SUM(ii.quantity) AS total_quantity,\n" +
            "    ist.type_name AS sale_type,\n" +
            "    ii.sale_date,\n" +
            "    ii.item_cost,\n" +
            "    ii.isOnSale\n" +
            "FROM\n" +
            "    itemSales AS ii\n" +
            "JOIN\n" +
            "    items AS i ON i.item_id = ii.item_id\n" +
            "JOIN\n" +
            "    itemSaleTypes AS ist ON ist.type_id = ii.type_id\n" +
            "JOIN\n" +
            "    orders AS o ON o.order_id = ii.order_id\n" +
            "JOIN\n" +
            "    users AS u ON u.user_id = o.user_id\n" +
            "WHERE\n" +
            "    o.order_id = :orderId\n" +
            "GROUP BY\n" +
            "    u.user_name, i.item_name, ist.type_name, ii.sale_date, ii.item_cost, ii.isOnSale\n" +
            "ORDER BY\n" +
            "    ii.sale_date DESC;",nativeQuery = true)
    List<Object[]> getItemSalesByOrder(@Param("orderId")int orderId);

    List<Order> findByUser(User user);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM orders AS o WHERE o.order_id = :orderId AND o.user_id =:userId);\n", nativeQuery = true)
    boolean isUserOrder(@Param("userId") int userId, @Param("orderId") int orderId);
}
