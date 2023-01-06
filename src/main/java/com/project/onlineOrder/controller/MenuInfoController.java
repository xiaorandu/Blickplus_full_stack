package com.project.onlineOrder.controller;

import com.project.onlineOrder.entity.MenuItem;
import com.project.onlineOrder.entity.Supplies;
import com.project.onlineOrder.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MenuInfoController {
    @Autowired
    private MenuInfoService menuInfoService;

    @RequestMapping(value = "/supplies/{suppliesId}/menu", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuItem> getMenus(@PathVariable("suppliesId") int restaurantId) {
        return menuInfoService.getAllMenuItem(restaurantId);
    }

    @RequestMapping(value = "/supplies", method = RequestMethod.GET)
    @ResponseBody //return value converts to json
    public List<Supplies> getSupplies() {
        return menuInfoService.getSupplies();
    }
}
