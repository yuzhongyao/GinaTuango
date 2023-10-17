package com.example.XingCheng.services;

import com.example.XingCheng.data.entities.ItemSale;
import com.example.XingCheng.data.entities.ItemSaleType;
import com.example.XingCheng.repositories.ItemSaleRepository;
import com.example.XingCheng.repositories.ItemSaleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemSaleTypeService {
    private final ItemSaleTypeRepository itemSaleTypeRepository;

    @Autowired
    public ItemSaleTypeService(ItemSaleTypeRepository itemSaleTypeRepository){
        this.itemSaleTypeRepository = itemSaleTypeRepository;
    }

    public List<ItemSaleType> getItemSaleTypes(){
        return itemSaleTypeRepository.findAll();
    }
}
