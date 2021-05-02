package com.company.UgonnaGuadalupeCapstone.controller;


import com.company.UgonnaGuadalupeCapstone.dao.ConsoleDao;
import com.company.UgonnaGuadalupeCapstone.dao.GamesDao;
import com.company.UgonnaGuadalupeCapstone.model.Console;
import com.company.UgonnaGuadalupeCapstone.model.Games;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ConsoleControllerTest {

    //wiring in the MVC object
    @Autowired
    private MockMvc mockMvc;

    //objectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    //list for testing
    @MockBean
    ConsoleDao console1;

    //List<Console> consoleList = console1.getAllConsoles();


    //Testing GET
    @Test
    public void shouldReturnAllConsoles() throws Exception{
        //convert Java Object to JSON
        String outputJson = mapper.writeValueAsString(console1.getAllConsoles());
        //ACT
        mockMvc.perform(get("/consoles"))   //Perform the GET request
                .andDo(print())             //Print results to console
                .andExpect(status().isOk()); //Assert (status code is 200)

    }

    //Testing POST
    @Test
    public void shouldReturnNewConsoleOnPostRequest() throws Exception {
        //Arrange
        Console inputConsole  = new Console();
        inputConsole.setModel("Toyota");
        inputConsole.setManufacturer("Microsoft");
        inputConsole.setMemoryAmount("35");
        inputConsole.setProcessor("Intel");
        inputConsole.setPrice(new BigDecimal("34.50"));
        inputConsole.setQuantity(10);

        //convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(inputConsole);

        Console outputConsole  = new Console();
        outputConsole.setModel("Toyota");
        outputConsole.setManufacturer("Microsoft");
        outputConsole.setMemoryAmount("35");
        outputConsole.setProcessor("Intel");
        outputConsole.setPrice(new BigDecimal("34.50"));
        outputConsole.setQuantity(10);
        outputConsole.setConsoleId(2);

        String outputJson = mapper.writeValueAsString(outputConsole);

        //Act
        mockMvc.perform(
                post("/consoles")                       // perform the post request
                    .content(inputJson)                 //set the request body
                    .contentType(MediaType.APPLICATION_JSON)    //tell the server it's in JSON format
        )
                .andDo(print())                         //print results to console
                .andExpect(status().isCreated());       //assert (status code is 201)

    }

    //testing GET by id
    @Test
    public void shouldReturnConsoleById() throws Exception {

        Console outputConsole  = new Console();
        outputConsole.setModel("Toyota");
        outputConsole.setManufacturer("Microsoft");
        outputConsole.setMemoryAmount("35");
        outputConsole.setProcessor("Intel");
        outputConsole.setPrice(new BigDecimal("34.50"));
        outputConsole.setQuantity(10);
        outputConsole.setConsoleId(3);

        String outputJson = mapper.writeValueAsString(outputConsole);

        mockMvc.perform(get("/consoles/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    //testing get by manufacturer
    @Test
    public void shouldReturnConsoleByManufacturer() throws Exception {

        Console outputConsole  = new Console();
        outputConsole.setModel("Toyota");
        outputConsole.setManufacturer("Microsoft");
        outputConsole.setMemoryAmount("35");
        outputConsole.setProcessor("Intel");
        outputConsole.setPrice(new BigDecimal("34.50"));
        outputConsole.setQuantity(10);
        outputConsole.setConsoleId(3);

        String outputJson = mapper.writeValueAsString(outputConsole);

        mockMvc.perform(get("/consoles/Microsoft"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    //testing PUT
    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {

        //This method returns nothing, so we're just checking for correct status code
        //in this case, code 204, which indicates no content


        //Arrange
        Console inputConsole  = new Console();
        inputConsole.setModel("Toyota");
        inputConsole.setManufacturer("Microsoft");
        inputConsole.setMemoryAmount("35");
        inputConsole.setProcessor("Intel");
        inputConsole.setPrice(new BigDecimal("34.50"));
        inputConsole.setQuantity(10);
        inputConsole.setConsoleId(5);

        //convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(inputConsole);

        mockMvc.perform(
                put("/consoles/5")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    //testing delete
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {

        mockMvc.perform(delete("/consoles/4"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
