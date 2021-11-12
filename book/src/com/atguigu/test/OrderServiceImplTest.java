package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.Order;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderServiceImplTest {

    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        System.out.println("订单号是：" + orderService.createOrder(cart, 2));
    }

    @Test
    public void showAllOrders() {
        System.out.println(orderService.showAllOrders());
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder(new Order("16366346078552",new Date(), new BigDecimal(2100),0,2));
    }

    @Test
    public void showOrderDetail() {
        System.out.println(orderService.showOrderDetail("16366346078552"));
    }

    @Test
    public void showMyOrders() {
        System.out.println(orderService.showMyOrders(2));
    }

    @Test
    public void receiverOrder() {
        orderService.receiverOrder(new Order("16366346078552",new Date(), new BigDecimal(2100),1,2));
    }
}