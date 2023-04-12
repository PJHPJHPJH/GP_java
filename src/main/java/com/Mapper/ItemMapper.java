package com.Mapper;

import com.Entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemMapper {
    public Item[] getItems();
    public Item getItemByItemId(@Param("itemId") Integer itemId);
}
