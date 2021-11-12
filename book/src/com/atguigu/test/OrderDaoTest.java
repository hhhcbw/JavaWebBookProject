package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("12345678",new Date(),new BigDecimal(100),0,2));
        orderDao.saveOrder(new Order("123", new Date(), new BigDecimal(1005), 0, 5));
        orderDao.saveOrder(new Order("456", new Date(), new BigDecimal(505), 1, 6));
        orderDao.saveOrder(new Order("789", new Date(), new BigDecimal(605), 2, 6));
    }

    @Test
    public void queryOrders() {
        System.out.println(orderDao.queryOrders());
    }

    @Test
    public void queryOrdersByUserId() {
        System.out.println(orderDao.queryOrdersByUserId(6));
    }

    @Test
    public void updateOrder() {
        orderDao.updateOrder(new Order("456", new Date(), new BigDecimal(611), 2, 6));
    }
}