package com.example.ginatuango.services;

import com.example.ginatuango.data.entities.ItemSale;
import com.example.ginatuango.data.entities.Order;
import com.example.ginatuango.repositories.ItemSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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

    public List<ItemSale> getItemSalesByOrder(Order order){return itemSaleRepository.findByOrder(order);}
    public void deleteItemSales(List<ItemSale> itemSales){itemSaleRepository.deleteAll(itemSales);}

    public List<ItemSale> getItemSalesByItemId(int id){
        return itemSaleRepository.getItemSalesByItemId(id);
    }


    public void saveItemSales(List<ItemSale> itemSales) {
        itemSaleRepository.saveAll(itemSales);
    }
}
