package com.mashirro.tacocloud.controller;

import com.mashirro.tacocloud.entity.*;
import com.mashirro.tacocloud.repository.UserInfoMapper;
import com.mashirro.tacocloud.service.IngredientService;
import com.mashirro.tacocloud.service.TacoService;
import com.mashirro.tacocloud.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.security.Principal;
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
    @Autowired
    private UserInfoMapper userInfoMapper;

    @ModelAttribute("design")
    public Taco taco() {
        System.out.println("taco()方法执行了...");
        return new Taco();
    }

//    @ModelAttribute("order")
//    public Order order() {
//        System.out.println("order()方法执行了...");
//        return new Order();
//    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {
        List<Ingredient> ingredients = ingredientService.findAll();
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        UserInfo userInfo = userInfoMapper.findByUsername(principal.getName());
        model.addAttribute("user", userInfo);
        //model.addAttribute("design", new Taco());
        return "design";
    }

    /**
     * 这种写法有bug,order的name属性会与请求参数绑定,改成下面那种写法
     */
//    @PostMapping
//    public String processDesign(Taco taco, @ModelAttribute Order order) {
//        Taco save = tacoService.save(taco);
//        order.addDesign(save);
//        return "redirect:/orders/current";
//    }
    @PostMapping
    public String processDesign(Taco taco, Model model, @AuthenticationPrincipal UserInfo userInfo) {
        Taco save = tacoService.save(taco);
        Order order = new Order();
        order.addDesign(save);
        order.setUserInfo(userInfo);
        model.addAttribute("order", order);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}
