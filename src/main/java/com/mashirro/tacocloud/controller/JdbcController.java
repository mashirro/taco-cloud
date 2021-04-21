package com.mashirro.tacocloud.controller;

import com.mashirro.tacocloud.entity.Ingredient;
import com.mashirro.tacocloud.entity.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/test")
public class JdbcController {
    private final static Logger logger = LoggerFactory.getLogger(JdbcController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/list")
    @ResponseBody
    public List<Ingredient> getList() {
        String sqlString = "select * from ingredient";
        //写法一:
//        List<Ingredient> list = jdbcTemplate.query(sqlString, new RowMapper<Ingredient>() {
//            /**
//             * mapRow:映射每行数据在结果集上,不要对结果集rs调用next(),它只映射当前行的值!
//             */
//            @Override
//            public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return new Ingredient(rs.getString("id"),
//                        rs.getString("name"),
//                        Ingredient.Type.valueOf(rs.getString("type")));
//            }
//        });

        //写法二:
        List<Ingredient> list = new ArrayList<>();
        try {
            int a = 1 / 0;
            list = jdbcTemplate.query(sqlString, this::mapRowToIngredient);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }


    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        return new Ingredient(rs.getString("id"),
                rs.getString("name"),
                Type.valueOf(rs.getString("type")));
    }

    @GetMapping("/getOne/{id}")
    @ResponseBody
    public Ingredient getOne(@PathVariable("id") String id) {
        String sqlString = "select * from ingredient where id = ? ";
        /**
         * 使用queryForObject的注意点:
         * 		1.最后一个参数是返回值的类型(只能是基本数据类型的包装类,如Integer、String)
         * 		2.如果想使用自定义的类型的返回值：new BeanPropertyRowMapper<>(xxx.class)
         */
        Ingredient entity = jdbcTemplate.queryForObject(sqlString, new BeanPropertyRowMapper<>(Ingredient.class), id);

        //写法二
        //Ingredient entity = jdbcTemplate.queryForObject(sqlString, this::mapRowToIngredient, id);
        return entity;
    }

    @GetMapping("/getCount")
    @ResponseBody
    public String getCount() {
        String sqlString = "select count(*) from ingredient";
        Integer count = jdbcTemplate.queryForObject(sqlString, Integer.class);
        return "总数为" + count;
    }
}
