package com.company.authorizationservice.controller;

import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
public class UserInfoController {

    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public Principal getUserInfo(Principal principal) {
        System.out.println("CALLED userinfo");
        return principal;
    }
}
