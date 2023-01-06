package com.project.onlineOrder.service;

import com.project.onlineOrder.dao.CustomerDao;
import com.project.onlineOrder.entity.Cart;
import com.project.onlineOrder.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomerService {
    private CustomerDao customerDao;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerDao customerDao, PasswordEncoder passwordEncoder) throws IOException {
//        if () {
//            throw new UserAlreadyExistException("User already exists");
//        }
        this.customerDao = customerDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void signUp(Customer customer){
        Cart cart = new Cart();
        customer.setCart(cart);

        customer.setEnabled(true);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerDao.signUp(customer);
    }


    public Customer getCustomer(String email) {
        return customerDao.getCustomer(email);
    }

}
