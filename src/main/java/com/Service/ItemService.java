package com.Service;


import com.Entity.Item;
import com.Mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ItemMapper itemMapper;

    //获得所有的商品信息
    public Item[] getItems() {
        return itemMapper.getItems();
    }
    //根据商品ID获得商品信息
    public Item getItemByItemId(Integer itemId) {
        return itemMapper.getItemByItemId(itemId);
    }

}
