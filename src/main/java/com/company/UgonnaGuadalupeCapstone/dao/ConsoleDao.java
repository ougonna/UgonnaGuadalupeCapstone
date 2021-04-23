package com.company.UgonnaGuadalupeCapstone.dao;

import com.company.UgonnaGuadalupeCapstone.model.Console;

import java.util.List;

public interface ConsoleDao {

    Console addConsole (Console console); //create
    Console getConsole (int id); //read
    List<Console> getAllConsoles(); //read all
    List<Console> getConsoleByManufacturer(String manufacturer);
    void updateConsole (Console console); //update
    void deleteConsole (int id); //delete

}
