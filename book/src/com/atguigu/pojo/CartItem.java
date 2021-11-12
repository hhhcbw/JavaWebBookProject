package com.atguigu.pojo;

import java.math.BigDecimal;

/**
 * 购物车的商品项
 */
public class CartItem {
    private Integer id; //编号
    private String name; //名称
    private Integer count; //数量
    private BigDecimal price; //单价
    private BigDecimal totalprice; //总价

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public CartItem() {
    }

    public CartItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalprice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalprice = totalprice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalprice=" + totalprice +
                '}';
    }
}
