package com.company.UgonnaGuadalupeCapstone.controller;

import com.company.UgonnaGuadalupeCapstone.dao.ConsoleDao;
import com.company.UgonnaGuadalupeCapstone.dao.TshirtDao;
import com.company.UgonnaGuadalupeCapstone.model.Console;
import com.company.UgonnaGuadalupeCapstone.model.Tshirt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
@WebMvcTest(TshirtController.class)

public class TshirtControllerTest {

    //wiring in the MVC object
    @Autowired
    private MockMvc mockMvc;

    //objectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    //list for testing
    @Autowired
    TshirtDao tshirtDao;

    List<Tshirt> tshirtList = tshirtDao.getAllTshirt();


    @Test
    public void shouldReturnAllTshirts() throws Exception {

        // Convert Java object to JSON
        String outputJson = mapper.writeValueAsString(tshirtList);
        // ACT
        mockMvc.perform(get("/tshirts"))                // Perform the GET request
                .andDo(print())                          // Print results to console
                .andExpect(status().isOk());              // ASSERT (status code is 200)
    }

    //testing post
    @Test
    public void shouldReturnNewTshirtOnPostRequest() throws Exception {

        Tshirt inputTshirt = new Tshirt();
        inputTshirt.setSize("small");
        inputTshirt.setColor("blue");
        inputTshirt.setDescription("something");
        inputTshirt.setQuantity(5);
        inputTshirt.setPrice(new BigDecimal("14.50"));

        //convert Java object to JSON
        String inputJson = mapper.writeValueAsString(inputTshirt);

        Tshirt outputTshirt = new Tshirt();
        outputTshirt.setSize("small");
        outputTshirt.setColor("blue");
        outputTshirt.setDescription("something");
        outputTshirt.setQuantity(5);
        outputTshirt.setPrice(new BigDecimal("14.50"));
        outputTshirt.setTshirtId(3);

        String outputJson = mapper.writeValueAsString(outputTshirt);

        // ACT
        mockMvc.perform(
                post("/tshirts")                            // Perform the POST request
                        .content(inputJson)                       // Set the request body
                        .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
        )
                .andDo(print())                                // Print results to console
                .andExpect(status().isCreated());              // ASSERT (status code is 201)

    }

    //testing Get
    @Test
    public void shouldReturnTshirtById() throws Exception {

        Tshirt outputTshirt = new Tshirt();
        outputTshirt.setSize("small");
        outputTshirt.setColor("blue");
        outputTshirt.setDescription("something");
        outputTshirt.setQuantity(5);
        outputTshirt.setPrice(new BigDecimal("14.50"));
        outputTshirt.setTshirtId(4);

        String outputJson = mapper.writeValueAsString(outputTshirt);

        mockMvc.perform(get("tshirt/4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    //testing Get by Color
    @Test
    public void shouldReturnTshirtByColor() throws Exception {

        Tshirt outputTshirt = new Tshirt();
        outputTshirt.setSize("small");
        outputTshirt.setColor("blue");
        outputTshirt.setDescription("something");
        outputTshirt.setQuantity(5);
        outputTshirt.setPrice(new BigDecimal("14.50"));
        outputTshirt.setTshirtId(4);

        String outputJson = mapper.writeValueAsString(outputTshirt);

        mockMvc.perform(get("tshirt/blue"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    //testing Get by Size
    @Test
    public void shouldReturnTshirtBySize() throws Exception {

        Tshirt outputTshirt = new Tshirt();
        outputTshirt.setSize("small");
        outputTshirt.setColor("blue");
        outputTshirt.setDescription("something");
        outputTshirt.setQuantity(5);
        outputTshirt.setPrice(new BigDecimal("14.50"));
        outputTshirt.setTshirtId(4);

        String outputJson = mapper.writeValueAsString(outputTshirt);

        mockMvc.perform(get("tshirt/small"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    //testing PUT
    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {

        Tshirt inputTshirt = new Tshirt();
        inputTshirt.setSize("small");
        inputTshirt.setColor("blue");
        inputTshirt.setDescription("something");
        inputTshirt.setQuantity(6);
        inputTshirt.setPrice(new BigDecimal("14.50"));

        //convert Java object to JSON
        String inputJson = mapper.writeValueAsString(inputTshirt);

        mockMvc.perform(
                put("/tshirt/6")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());


    }

    //testing Delete
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content
        mockMvc.perform(delete("/tshirt/6"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}
