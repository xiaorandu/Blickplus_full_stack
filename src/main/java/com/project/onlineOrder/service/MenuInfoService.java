package com.project.onlineOrder.service;


import com.project.onlineOrder.dao.MenuInfoDao;
import com.project.onlineOrder.entity.MenuItem;
import com.project.onlineOrder.entity.Supplies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//controller cannot directly get access to the database
@Service
public class MenuInfoService {
    @Autowired
    private MenuInfoDao menuInfoDao;

    public List<Supplies> getSupplies() {
        return menuInfoDao.getSupplies();
    }

    public List<MenuItem> getAllMenuItem(int suppliesId) {
        return menuInfoDao.getAllMenuItem(suppliesId);
    }

    public MenuItem getMenuItem(int id) {
        return menuInfoDao.getMenuItem(id);
    }
}
