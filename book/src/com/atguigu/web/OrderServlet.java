package com.atguigu.web;

import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.WebUtils;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return ;
        }

        Integer userId = loginUser.getId();
        String orderId = orderService.createOrder(cart, userId);

        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    /**
     * 展示全部订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.通过orderService查询所有订单
        List<Order> orders = orderService.showAllOrders();
        // 2.把全部订单保存到Request域
        req.setAttribute("orders", orders);
        // 3.请求转发到 /pages/manager/order_manager.jsp
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    /**
     * 展示我的订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return ;
        }

        Integer userId = loginUser.getId();
        List<Order> myOrders = orderService.showMyOrders(userId);

        req.getSession().setAttribute("myOrders", myOrders);

        resp.sendRedirect(req.getContextPath()+"/pages/order/order.jsp");
    }

    /**
     * 显示订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取Orderid
        String orderId = req.getParameter("orderId");
        if (orderId != null && orderId != "") {
            // 2. 查询订单项
            List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
            // 3. 把订单项保存到Request域
            req.setAttribute("orderItems", orderItems);
            // 4. 请求转发到 /pages/order/order_detail.jsp
            req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req, resp);
        }
    }

    /**
     * 发货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取Order
        Order order = WebUtils.copyParamToBean(req.getParameterMap(), new Order());
        // 2. 调用orderService的sendOrder方法发货
        orderService.sendOrder(order);
        // 3. 重定向回 /orderServlet?action=showAllOrders
        resp.sendRedirect(req.getContextPath() + "/orderServlet?action=showAllOrders");
    }

    /**
     * 签收
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取Order
        Order order = WebUtils.copyParamToBean(req.getParameterMap(), new Order());
        // 2. 调用orderService的receiverOrder方法签收
        orderService.receiverOrder(order);
        // 3. 重定向回 /orderServlet?action=showMyOrders
        resp.sendRedirect(req.getContextPath() + "/orderServlet?action=showMyOrders");
    }
}
