package com.mashirro.tacocloud.repository;

import com.mashirro.tacocloud.entity.Taco;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface TacoMapper {
    @Select("select * from taco where id in (select tacoId from order_tacos where tacoOrderId = #{id})")
    List<Taco> getTacosByOrderId(String id);
}
