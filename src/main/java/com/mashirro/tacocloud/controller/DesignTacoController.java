package com.mashirro.tacocloud.controller;

import com.mashirro.tacocloud.entity.Ingredient;
import com.mashirro.tacocloud.entity.Order;
import com.mashirro.tacocloud.entity.Taco;
import com.mashirro.tacocloud.entity.Type;
import com.mashirro.tacocloud.service.IngredientService;
import com.mashirro.tacocloud.service.TacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private TacoService tacoService;

    @ModelAttribute("design")
    public Taco taco() {
        System.out.println("taco()方法执行了...");
        return new Taco();
    }

    @ModelAttribute("order")
    public Order order() {
        System.out.println("order()方法执行了...");
        return new Order();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = ingredientService.findAll();
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        //model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(Taco taco, @ModelAttribute Order order) {
        Taco save = tacoService.save(taco);
        order.addDesign(save);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}
