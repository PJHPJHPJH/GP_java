package com.Service;

import com.Mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartMapper cartMapper;

    //查询

    //根据用户ID查询订单号
    public Integer[] getCartIdsByUserId(Integer userId) {
        return cartMapper.getCartIdsByUserId(userId);
    }

    //根据用户ID查询订单
    public Integer[] getItemIdsByUserId(Integer userId) {
        return cartMapper.getItemIdsByUserId(userId);
    }

    //根据用户ID查询商品数量
    public Integer[] getNumsByUserId(Integer userId) {
        return cartMapper.getNumsByUserId(userId);
    }


    //根据用户ID和商品ID查询记录
    public Integer getOrderIdByUserIdAndItemId(Integer userId, Integer itemId) {
        return cartMapper.getOrderIdByUserIdAndItemId(userId, itemId);
    }


    //删除
    public Integer deleteCartById(Integer orderId) {
        return cartMapper.deleteCartById(orderId);
    }


    //更新

    //修改商品数量
    public Integer updateNumById(Integer orderId, Integer num) {
        return cartMapper.updateNumById(orderId, num);
    }
    //添加到购物车
    public Integer addCart(Integer userId, Integer itemId){
        return cartMapper.addCart(userId, itemId, 1);
    }



}
