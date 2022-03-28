package com.keeper.clothingkeeper.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class test {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String Test(){
        return "This is a test";
    }
}
