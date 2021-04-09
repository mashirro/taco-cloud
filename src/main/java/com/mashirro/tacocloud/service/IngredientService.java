package com.mashirro.tacocloud.service;


import com.mashirro.tacocloud.entity.Ingredient;
import com.mashirro.tacocloud.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Service
public class IngredientService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Ingredient> findAll() {
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
        List<Ingredient> list = jdbcTemplate.query(sqlString, this::mapRowToIngredient);
        return list;
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        return new Ingredient(rs.getString("id"),
                rs.getString("name"),
                Type.valueOf(rs.getString("type")));
    }


    public Ingredient findOne(String id) {
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

    public Integer getTotalCount() {
        String sqlString = "select count(*) from ingredient";
        Integer count = jdbcTemplate.queryForObject(sqlString, Integer.class);
        return count;
    }
}
