package com.Controller;


import com.Entity.Item;
import com.Service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@CrossOrigin(origins = "*")
@Controller
public class ItemController {

    @Autowired
    ItemService itemService;


    //获得所有商品
    @ResponseBody
    @PostMapping("/getAllItems")
    public String getAllItems(@RequestBody Map<String, Object> map) {
        Item[] items = itemService.getItems();
//        System.out.println(items[1]);
        //将数据进行JSON封装
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(items);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //测试数据输出
//        System.out.println(json);
        return json;
    }




    //根据商品ID获得商品信息
    @ResponseBody
    @PostMapping("/getItem")
    public String getItem(@RequestBody Map<String, Object> map) {
        Integer itemId = Integer.parseInt((String) map.get("id"));
        Item item = itemService.getItemByItemId(itemId);
        //将数据进行JSON封装
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(item);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //测试数据输出
//        System.out.println(json);
        return json;

    }
}
