package com.mashirro.tacocloud.repository;


import com.mashirro.tacocloud.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from tacoorder")
    List<Order> findAllOrders();
}
