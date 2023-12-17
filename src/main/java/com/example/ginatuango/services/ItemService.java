package com.example.ginatuango.services;

import com.example.ginatuango.data.entities.Item;
import com.example.ginatuango.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    public Item getItem(int id){return itemRepository.getReferenceById(id);}

    public void updateItem(Item item){
        itemRepository.save(item);
    }

    public void insertNewItem(Item item){
        itemRepository.save(item);
    }

    public void deleteItem(Item item){
        itemRepository.delete(item);
    }

}
