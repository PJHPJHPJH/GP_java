package com.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartMapper {
    //查询


    //根据用户ID查询订单号
    public Integer[] getCartIdsByUserId(@Param("userId") Integer userId);
    //根据用户ID查询订单
    public Integer[] getItemIdsByUserId(@Param("userId") Integer userId);
    //根据用户ID查询商品数量
    public Integer[] getNumsByUserId(@Param("userId") Integer userId);



    //根据用户ID和商品ID查询一条记录
    public Integer getOrderIdByUserIdAndItemId(@Param("userId") Integer userId,
                                               @Param("itemId") Integer itemId);


    //删除
    public Integer deleteCartById(@Param("orderId") Integer orderId);

    //修改

    //修改商品数量
    public Integer updateNumById(@Param("orderId") Integer orderId,
                                 @Param("num") Integer num);
    //添加到购物车
    public Integer addCart(@Param("userId") Integer userId,
                           @Param("itemId") Integer itemId,
                           @Param("num") Integer num);
}
