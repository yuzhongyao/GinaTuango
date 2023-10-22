package com.example.ginatuango.services;

import com.example.ginatuango.data.entities.ItemSaleType;
import com.example.ginatuango.repositories.ItemSaleTypeRepository;
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
