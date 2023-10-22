package com.example.ginatuango.repositories;

import com.example.ginatuango.data.entities.ItemSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemSaleRepository extends JpaRepository<ItemSale, Integer> {
}
