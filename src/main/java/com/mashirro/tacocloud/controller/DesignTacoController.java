package com.mashirro.tacocloud.controller;


import com.mashirro.tacocloud.entity.Ingredient;
import com.mashirro.tacocloud.entity.Taco;
import com.mashirro.tacocloud.entity.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "小麦饼", Type.WRAP),
                new Ingredient("COTO", "玉米饼", Type.WRAP),
                new Ingredient("GRBF", "碎牛肉", Type.PROTEIN),
                new Ingredient("CARN", "墨西哥小肉", Type.PROTEIN),
                new Ingredient("TMTO", "番茄丁", Type.VEGGIES),
                new Ingredient("LETC", "生菜", Type.VEGGIES),
                new Ingredient("CHED", "切达干酪", Type.CHEESE),
                new Ingredient("JACK", "蒙德勒杰克奶酪", Type.CHEESE),
                new Ingredient("SLSA", "萨尔萨辣酱", Type.SAUCE),
                new Ingredient("SRCR", "酸奶油", Type.SAUCE)
        );

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }


    public List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }


    @PostMapping
    public String processDesign(Taco taco) {
        //save the taco design...we'll do this in chapter 3
        //log.info("Processing design: " + taco);
        return "redirect:/orders/current";
    }
}
