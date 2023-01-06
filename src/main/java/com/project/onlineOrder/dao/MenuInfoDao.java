package com.project.onlineOrder.dao;

import com.project.onlineOrder.entity.MenuItem;
import com.project.onlineOrder.entity.Supplies;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuInfoDao {
    @Autowired
    private SessionFactory sessionFactory; //get the session object to do the CRUD


    //SELECT * FROM restaurant WHERE name = ?
    public List<Supplies> getSupplies() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Supplies> criteria = builder.createQuery(Supplies.class);
            criteria.from(Supplies.class);
            return session.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<MenuItem> getAllMenuItem(int suppliesId) {
        try (Session session = sessionFactory.openSession()) {
            Supplies supplies = session.get(Supplies.class, suppliesId);
            if (supplies != null) {
                return supplies.getMenuItemList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    // add item into cart
    public MenuItem getMenuItem(int menuItemId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MenuItem.class, menuItemId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
