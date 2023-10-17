package com.example.XingCheng.repositories;

import com.example.XingCheng.data.entities.ItemSaleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemSaleTypeRepository extends JpaRepository<ItemSaleType, Integer>{
}
