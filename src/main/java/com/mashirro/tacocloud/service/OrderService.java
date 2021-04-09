package com.mashirro.tacocloud.service;


import com.mashirro.tacocloud.entity.Order;
import com.mashirro.tacocloud.entity.Taco;
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

    @Transactional
    public Order save(Order order) {
        order.setId(UUID.randomUUID().toString());
        order.setCreateTime(new Date());
        jdbcTemplate.update("insert into tacoorder VALUES (?,?,?,?,?,?,?,?,?,?)",
                order.getId(), order.getName(), order.getStreet(), order.getCity(), order.getState(),
                order.getZip(), order.getCcNumber(), order.getCcExpiration(), order.getCcCVV(), order.getCreateTime());

        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            jdbcTemplate.update("insert into order_tacos (tacoOrderId, tacoId) VALUES (?,?)",
                    order.getId(), taco.getId());
        }
        return order;
    }
}
