package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    /**
     * 保存订单
     * @param order
     * @return
     */
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";

        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }

    /**
     * 查询全部订单
     * @return
     */
    @Override
    public List<Order> queryOrders() {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order";
        return queryForList(Order.class, sql);
    }

    /**
     * 根据UserId查询订单
     * @param id
     * @return
     */
    @Override
    public List<Order> queryOrdersByUserId(Integer id) {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order where user_id=?";
        return queryForList(Order.class, sql, id);
    }

    /**
     * 修改订单
     * @param order
     * @return
     */
    @Override
    public int updateOrder(Order order) {
        String sql = "update t_order set `create_time`=?,`price`=?,`status`=? where order_id=?";
        return update(sql, order.getCreateTime(), order.getPrice(), order.getStatus(), order.getOrderId());
    }
}
