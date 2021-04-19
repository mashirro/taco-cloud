package com.mashirro.tacocloud.repository;


import com.mashirro.tacocloud.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {

    UserInfo findByUsername(String username);


    /****   mybatis生成器              ***/
    int deleteByPrimaryKey(String id);
    int insert(UserInfo userInfo);
    int insertSelective(UserInfo userInfo);
    UserInfo selectByPrimaryKey(String id);
    int updateByPrimaryKeySelective(UserInfo userInfo);
    int updateByPrimaryKey(UserInfo userInfo);
}
