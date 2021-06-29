package com.mashirro.tacocloud.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashirro.tacocloud.entity.Order;
import com.mashirro.tacocloud.entity.Taco;
import com.mashirro.tacocloud.entity.UserInfo;
import com.mashirro.tacocloud.repository.OrderMapper;
import com.mashirro.tacocloud.repository.TacoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TacoMapper tacoMapper;

    @Transactional
    public Order save(Order order) {
        order.setId(UUID.randomUUID().toString());
        order.setCreateTime(new Date());
        jdbcTemplate.update("insert into tacoorder VALUES (?,?,?,?,?,?,?,?,?,?)",
                order.getId(), order.getDeliveryName(), order.getDeliveryStreet(), order.getDeliveryCity(), order.getDeliveryState(),
                order.getDeliveryZip(), order.getCcNumber(), order.getCcExpiration(), order.getCcCVV(), order.getCreateTime());

        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            jdbcTemplate.update("insert into order_tacos (tacoOrderId, tacoId) VALUES (?,?)",
                    order.getId(), taco.getId());
        }
        return order;
    }

    public List<Order> findAllOrders() {
        List<Order> orders = orderMapper.findAllOrders();
        orders.forEach(order -> {
            order.setTacos(tacoMapper.getTacosByOrderId(order.getId()));
        });
        return orders;
    }
}
