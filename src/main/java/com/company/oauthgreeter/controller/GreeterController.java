package com.company.oauthgreeter.controller;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class GreeterController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Principal principal) {

        return "Hello, " + principal.getName() + "!";
    }
}
