package com.example.XingCheng.repositories;

import com.example.XingCheng.data.entities.ItemSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemSaleRepository extends JpaRepository<ItemSale, Integer> {
}
