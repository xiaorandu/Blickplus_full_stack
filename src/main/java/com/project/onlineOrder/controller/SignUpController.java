package com.project.onlineOrder.controller;

import com.project.onlineOrder.entity.Customer;
import com.project.onlineOrder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class SignUpController {
    private CustomerService customerService;

    @Autowired
    public SignUpController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST) //how to call this method?
    @ResponseStatus(value = HttpStatus.CREATED)//how to get the data?
    //RequestBody: convert (request body)JSON to (backend)object ~POST
    //RequestPara: ~GET
    public void signUp(@RequestBody Customer customer){
        customerService.signUp(customer);
    }
}

