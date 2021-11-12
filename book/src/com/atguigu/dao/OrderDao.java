package com.atguigu.dao;

import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public interface OrderDao {
    public int saveOrder(Order order);

    public List<Order> queryOrders();

    public List<Order> queryOrdersByUserId(Integer id);

    public int updateOrder(Order order);
}
