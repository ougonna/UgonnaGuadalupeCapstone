package com.company.UgonnaGuadalupeCapstone.dao;

import com.company.UgonnaGuadalupeCapstone.model.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleDaoTest {

    @Autowired
    ConsoleDao consoleDao;
//    @Autowired
//    GamesDao gamesDao;
//    @Autowired
//    TshirtDao tshirtDao;
//    @Autowired
//    ProcessingDao processingDao;
//    @Autowired
//    TaxDao taxDao;

    @Before
    public void setUp() throws Exception {

        //clean test db
        List<Console> tConsole = consoleDao.getAllConsoles();
        for (Console t : tConsole){
            consoleDao.deleteConsole(t.getConsoleId());
        }
    }

    @Test
    public void addGetDeleteConsole(){
        Console console = new Console();
        console.setModel("Toyota");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("35");
        console.setProcessor("Intel");
        console.setPrice(new BigDecimal("34.50"));
        console.setQuantity(10);

        console = consoleDao.addConsole(console);
        Console console1 = consoleDao.getConsole(console.getConsoleId());
        assertEquals(console1, console);

        consoleDao.deleteConsole(console.getConsoleId());
        console1 = consoleDao.getConsole(console.getConsoleId());
        assertNull(console1);


    }

    @Test
    public void getAllConsoles(){

        Console console = new Console();
        console.setModel("Toyota");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("35");
        console.setProcessor("Intel");
        console.setPrice(new BigDecimal("34.50"));
        console.setQuantity(10);

        consoleDao.addConsole(console);

        Console console1 = new Console();
        console1.setModel("Length");
        console1.setManufacturer("Micron");
        console1.setMemoryAmount("35");
        console1.setProcessor("Intel");
        console1.setPrice(new BigDecimal("32.50"));
        console1.setQuantity(20);

        consoleDao.addConsole(console1);

        List<Console> consoles = consoleDao.getAllConsoles();

        assertEquals(2, consoles.size());

    }

    @Test
    public void getConsoleByManufacturer(){

        Console console = new Console();
        console.setModel("Toyota");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("35");
        console.setProcessor("Intel");
        console.setPrice(new BigDecimal("34.50"));
        console.setQuantity(10);

        consoleDao.addConsole(console);

        Console console1 = new Console();
        console1.setModel("Length");
        console1.setManufacturer("Microsoft");
        console1.setMemoryAmount("35");
        console1.setProcessor("Intel");
        console1.setPrice(new BigDecimal("32.50"));
        console1.setQuantity(20);

        consoleDao.addConsole(console1);

        List<Console> consoleList = consoleDao.getConsoleByManufacturer("Microsoft");

        assertEquals(consoleList.size(), 2);


    }

    @Test
    public void updateConsole(){
        Console console = new Console();
        console.setModel("Toyota");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("35");
        console.setProcessor("Intel");
        console.setPrice(new BigDecimal("34.50"));
        console.setQuantity(10);

        console = consoleDao.addConsole(console);

        console.setQuantity(500);
        console.setModel("Honda");
        consoleDao.updateConsole(console);

        Console console1 = consoleDao.getConsole(console.getConsoleId());
        assertEquals(console, console1);
    }



}
