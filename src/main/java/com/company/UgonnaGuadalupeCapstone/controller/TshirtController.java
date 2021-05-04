package com.company.UgonnaGuadalupeCapstone.controller;

import com.company.UgonnaGuadalupeCapstone.dao.TshirtDao;

import com.company.UgonnaGuadalupeCapstone.model.Tshirt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@CacheConfig(cacheNames = {"tshirts"})
public class TshirtController {

    @Autowired
    TshirtDao dao;

    public TshirtController(TshirtDao dao){this.dao = dao;}

    //add Tshirt
    @RequestMapping(value = "/tshirts", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Secured({"ROLE_MANAGER"})
    public Tshirt createTshirt(@RequestBody @Valid Tshirt tshirt){
        System.out.println("creating tshirt");
        return dao.addTshirt(tshirt);
    }

    //get tshirt by id
    @RequestMapping(value = "/tshirts/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public Tshirt getTshirt(@PathVariable int id){
        System.out.println("getting tshirt id = " + id);
        return dao.getTshirt(id);
    }

    //get tshirt by color
    @RequestMapping(value = "/tshirts/color/{color}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public List<Tshirt> getAllTshirtByColor(@PathVariable String color){
        System.out.println("Getting tshirts by color.." + color);
        return dao.getTshirtByColor(color);
    }

    //get tshirt by Size
    @RequestMapping(value = "/tshirts/size/{size}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public List<Tshirt> getAllTshirtBySize(@PathVariable String size){
        System.out.println("Getting tshirts by size.." + size);
        return dao.getTshirtBySize(size);
    }

    //get all tshirts
    @RequestMapping(value = "/tshirts", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public List<Tshirt> getAllTshirt(){
        System.out.println("Getting all tshirts...");
        return dao.getAllTshirt();
    }

    //update tshirt
    @RequestMapping(value = "/tshirts", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN"})
    public void updateTshirt(@RequestBody Tshirt tshirt){
        System.out.println("Updating Tshirt id = " + tshirt.getTshirtId());
        dao.updateTshirt(tshirt);
    }

    //delete tshirt
    @RequestMapping(value = "/tshirts/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN"})
    public void deleteTshirt(@PathVariable int id){
        System.out.println("Deleting tshirt id = " + id);
        dao.deleteTshirt(id);
    }

}
