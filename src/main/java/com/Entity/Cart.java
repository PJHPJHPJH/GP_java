package com.Entity;

import lombok.Data;

@Data
public class Cart {
    private Integer cartId;
    private Item item;
    private Integer num;
}
