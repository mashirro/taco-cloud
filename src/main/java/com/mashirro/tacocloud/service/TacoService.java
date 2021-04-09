package com.mashirro.tacocloud.service;


import com.mashirro.tacocloud.entity.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class TacoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public Taco save(Taco taco) {
        taco.setId(UUID.randomUUID().toString());
        taco.setCreateTime(new Date());
        jdbcTemplate.update("insert into taco (id,name,createTime) VALUES (?,?,?)"
                , taco.getId(), taco.getName(), taco.getCreateTime());


        for (String ingredientId : taco.getIngredients()) {
            jdbcTemplate.update("insert into taco_ingredients (tacoId, ingredientId) VALUES (?,?)",
                    taco.getId(), ingredientId);
        }
        return taco;
    }
}
