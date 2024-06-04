package com.Foodcourt.fc.service;

import com.Foodcourt.fc.Entity.Items;
import com.Foodcourt.fc.dto.ItemsDetailsDTO;

import java.util.List;

public interface ItemService {

    public Items save(ItemsDetailsDTO itemsDetailsDTO);
    public List<Items> fetch(String category);

    public List<String> category();

    public void delete(int id);

    public void modify(Items items);

    public List<Items> fetchall();
}
