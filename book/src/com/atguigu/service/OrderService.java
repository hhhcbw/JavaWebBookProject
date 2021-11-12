package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.Page;

import java.util.List;

public interface OrderService {

    /**
     * 创建订单
     * @param cart
     * @param userId
     * @return
     */
    public String createOrder(Cart cart, Integer userId);

    /**
     * 展示所有订单
     * @return
     */
    public List<Order> showAllOrders();

    /**
     * 发货
     * @param order
     */
    public void sendOrder(Order order);

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    public List<OrderItem> showOrderDetail(String id);

    /**
     * 展示我的订单
     * @param id
     * @return
     */
    public List<Order> showMyOrders(Integer id);

    /**
     * 签收
     * @param order
     */
    public void receiverOrder(Order order);
}
