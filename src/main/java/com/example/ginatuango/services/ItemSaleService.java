package com.example.ginatuango.services;

import com.example.ginatuango.data.entities.ItemSale;
import com.example.ginatuango.repositories.ItemSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemSaleService {


    private final ItemSaleRepository itemSaleRepository;

    @Autowired
    public ItemSaleService(ItemSaleRepository itemSaleRepository){
        this.itemSaleRepository = itemSaleRepository;
    }

    public List<ItemSale> getItemSales(){
        return itemSaleRepository.findAll();
    }

}