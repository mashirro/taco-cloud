package com.mashirro.tacocloud.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashirro.tacocloud.entity.Order;
import com.mashirro.tacocloud.entity.OrderProps;
import com.mashirro.tacocloud.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;


@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProps orderProps;

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(Order order, SessionStatus sessionStatus) {
        logger.info("Order submitted: " + order);
        orderService.save(order);
        //订单保存完成之后,我们就不需要再session中持有它了
        //实际上如果我们不把它清理掉,那么订单就会继续保留在session中,其中包括与之关联的taco,下一次的订单将会从旧订单保存的taco开始
        //所以processOrder方法请求了一个sessionStatus参数并调用了它的setComplete方法重置session
        sessionStatus.setComplete();
        return "redirect:/";
    }


    /**
     * 展示所有订单!(分页展示)
     *
     * @return
     */
    @GetMapping
    public String ordersForAll(Model model) {
        //分页
        //PageHelper.startPage(0, 1);
        PageHelper.startPage(0, orderProps.getPageSize());
        List<Order> allOrders = orderService.findAllOrders();
        PageInfo<Order> userOrderPage = new PageInfo<>(allOrders);
        model.addAttribute("orders", userOrderPage.getList());
        return "orderList";
    }
}
