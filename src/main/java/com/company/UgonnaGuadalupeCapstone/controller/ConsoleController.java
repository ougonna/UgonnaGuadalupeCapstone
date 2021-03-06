package com.company.UgonnaGuadalupeCapstone.controller;

import com.company.UgonnaGuadalupeCapstone.dao.ConsoleDao;
import com.company.UgonnaGuadalupeCapstone.model.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CacheConfig(cacheNames = {"consoles"})
public class ConsoleController {

    @Autowired
    ConsoleDao dao;

    public ConsoleController(ConsoleDao dao){
        this.dao = dao;
    }


    // add console
    @RequestMapping(value = "/consoles", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Secured({"ROLE_MANAGER"})
    public Console createConsole(@RequestBody Console console){
        System.out.println("creating console");
        return dao.addConsole(console);
    }

    //get console by id
    @RequestMapping(value = "/consoles/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public Console getConsoles(@PathVariable int id){
        System.out.println("getting console id = " + id);
        return dao.getConsole(id);
    }

    //get console by manufacturer
    @RequestMapping(value = "/consoles/manufacturer/{manufacturer}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public List<Console> getAllConsolesByManufacturer(@PathVariable String manufacturer){
        System.out.println("Getting consoles by manufacturer.." + manufacturer);
        return dao.getConsoleByManufacturer(manufacturer);
    }

    //get all consoles
    @RequestMapping(value = "/consoles", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public List<Console> getAllConsoles(){
        System.out.println("Getting all consoles...");
        return dao.getAllConsoles();
    }


    //update console
    @RequestMapping(value = "/consoles", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN"})
    public void updateConsole(@RequestBody @Valid Console console){
        System.out.println("Updating Console id = " + console.getConsoleId());
        dao.updateConsole(console);
    }

    //delete console
    @RequestMapping(value = "/consoles/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN"})
    public void deleteConsole(@PathVariable int id){
        System.out.println("Deleting console id = " + id);
        dao.deleteConsole(id);
    }
}
