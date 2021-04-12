package com.mashirro.tacocloud.controller;


import com.mashirro.tacocloud.entity.Order;
import com.mashirro.tacocloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(Order order, SessionStatus sessionStatus) {
        //log.info("Order submitted: " + order);
        orderService.save(order);
        //订单保存完成之后,我们就不需要再session中持有它了
        //实际上如果我们不把它清理掉,那么订单就会继续保留在session中,其中包括与之关联的taco,下一次的订单将会从旧订单保存的taco开始
        //所以processOrder方法请求了一个sessionStatus参数并调用了它的setComplete方法重置session
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
