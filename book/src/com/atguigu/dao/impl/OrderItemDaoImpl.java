package com.atguigu.dao.impl;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";

        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    /**
     * 根据order_id查询订单项
     * @param id
     * @return
     */
    @Override
    public List<OrderItem> queryOrderItemsById(String id) {
        String sql = "select `id`,`name`,`count`,`price`,`total_price` totalPrice,`order_id` orderId from t_order_item where order_id=?";

        return queryForList(OrderItem.class, sql, id);
    }
}
