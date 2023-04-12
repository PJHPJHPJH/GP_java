package com.Controller;

import com.Entity.Cart;
import com.Entity.Item;
import com.Service.CartService;
import com.Service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javaws.IconUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;


//解决跨域问题
@CrossOrigin(origins = "*")
@Controller
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    ItemService itemService;

    @ResponseBody
    @PostMapping("/getCartItems")
    public String getCartItems(@RequestBody Map<String, Object> map) {
        Integer userId = Integer.parseInt((String) map.get("id"));
        System.out.println(userId);
        //获得所有购物车中的订单号
        Integer[] cartIds = cartService.getCartIdsByUserId(userId);
        //获得所有购物车中的商品号
        Integer[] itemIds = cartService.getItemIdsByUserId(userId);
        //获得对应的数量
        Integer[] nums = cartService.getNumsByUserId(userId);
        for (int i = 0; i < itemIds.length; i++){
            System.out.println("cartId:" + cartIds[i] + "itemId:" + itemIds[i] + " num:" + nums[i]);
        }

        //定义一个Cart类型数组保存返回结果
        ArrayList<Cart> carts = new ArrayList<Cart>();


        for (int i = 0; i < itemIds.length; i++){
            //根据商品号查询商品信息
            Item item = itemService.getItemByItemId(itemIds[i]);
            //将信息和数量封装到Cart类中
            Cart cart = new Cart();
            cart.setCartId(cartIds[i]);
            cart.setItem(item);
            cart.setNum(nums[i]);
            carts.add(cart);
        }
        //将数据进行JSON封装
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(carts);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //测试数据输出
        System.out.println(json);
        return json;
    }



    @ResponseBody
    @PostMapping("/deleteCart")
    public String deleteCart(@RequestBody Map<String, Object> map) {
        Integer orderId = Integer.parseInt((String) map.get("id"));
        int flag = cartService.deleteCartById(orderId);
        if (flag != 0) {
            return "true";
        }
        else{
            return "false";
        }
    }


    @ResponseBody
    @PostMapping("/changeNum")
    public String changeNum(@RequestBody Map<String, Object> map) {
        Integer orderId = Integer.parseInt((String) map.get("id"));
        Integer num = Integer.parseInt((String) map.get("num"));
        int flag = cartService.updateNumById(orderId, num);
        if (flag != 0) {
            return "ture";
        }
        else{
            return "false";
        }
    }



    @ResponseBody
    @PostMapping("/addCart")
    public String addCart(@RequestBody Map<String, Object> map) {
        Integer userId = Integer.parseInt((String) map.get("userId"));
        Integer itemId = Integer.parseInt((String) map.get("itemId"));

        //进行重复判断
        Integer flag = cartService.getOrderIdByUserIdAndItemId(userId, itemId);
        //进行判断
        if(flag != null) {
            return "false";
        }

        Integer result = cartService.addCart(userId, itemId);
        if(result != 0){
            return "true";
        }
        else{
            return "false";
        }
    }





}


