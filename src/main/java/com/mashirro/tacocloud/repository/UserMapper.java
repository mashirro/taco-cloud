package com.mashirro.tacocloud.repository;


import com.mashirro.tacocloud.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findBuUsername(String username);

}
