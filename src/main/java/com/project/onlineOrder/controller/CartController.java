package com.project.onlineOrder.controller;

import com.project.onlineOrder.entity.Cart;
import com.project.onlineOrder.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET) //getCart by username, so no need {cartId}
    @ResponseBody
    public Cart getCart(){
        return cartService.getCart();
    }
}
