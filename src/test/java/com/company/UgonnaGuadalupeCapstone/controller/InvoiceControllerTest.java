package com.company.UgonnaGuadalupeCapstone.controller;

import com.company.UgonnaGuadalupeCapstone.dao.GamesDao;
import com.company.UgonnaGuadalupeCapstone.dao.InvoiceDao;
import com.company.UgonnaGuadalupeCapstone.model.Games;
import com.company.UgonnaGuadalupeCapstone.model.Invoice;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InvoiceControllerTest {

    //wiring in the MVC Object
    @Autowired
    private MockMvc mockMvc;

    //objectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    //testing POST
    @Test
    public void shouldReturnNewInvoiceOnPostRequest() throws Exception {

        Invoice inputInvoice = new Invoice();
        inputInvoice.setName("Jack");
        inputInvoice.setStreet("WD");
        inputInvoice.setCity("Fsted");
        inputInvoice.setState("VI");
        inputInvoice.setZipcode("80051");
        inputInvoice.setItemType("idk");
        inputInvoice.setItemId(1);
        inputInvoice.setUnitPrice(new BigDecimal("1.00"));
        inputInvoice.setQuantity(1);
        inputInvoice.setSubtotal(new BigDecimal("1.00"));
        inputInvoice.setTax(new BigDecimal("1.00"));
        inputInvoice.setProcessingFee(new BigDecimal("1.00"));
        inputInvoice.setTotal(new BigDecimal("1.00"));

        //Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(inputInvoice);

        Invoice outputInvoice = new Invoice();
        outputInvoice.setName("Jack");
        outputInvoice.setStreet("WD");
        outputInvoice.setCity("Fsted");
        outputInvoice.setState("VI");
        outputInvoice.setZipcode("80051");
        outputInvoice.setItemType("idk");
        outputInvoice.setItemId(1);
        outputInvoice.setUnitPrice(new BigDecimal("1.00"));
        outputInvoice.setQuantity(1);
        outputInvoice.setSubtotal(new BigDecimal("1.00"));
        outputInvoice.setTax(new BigDecimal("1.00"));
        outputInvoice.setProcessingFee(new BigDecimal("1.00"));
        outputInvoice.setTotal(new BigDecimal("1.00"));
        outputInvoice.setInvoiceID(2);

        String outputJson = mapper.writeValueAsString(outputInvoice);


        // ACT
        mockMvc.perform(
                post("/invoice")                            // Perform the POST request
                        .content(inputJson)                       // Set the request body
                        .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
        )
                .andDo(print())                                // Print results to console
                .andExpect(status().isCreated());              // ASSERT (status code is 201)


    }


    //testing GET by id
    @Test
    public void shouldReturnInvoiceBYId() throws Exception {

        Invoice outputInvoice = new Invoice();
        outputInvoice.setName("Jack");
        outputInvoice.setStreet("WD");
        outputInvoice.setCity("Fsted");
        outputInvoice.setState("VI");
        outputInvoice.setZipcode("80051");
        outputInvoice.setItemType("idk");
        outputInvoice.setItemId(1);
        outputInvoice.setUnitPrice(new BigDecimal("1.00"));
        outputInvoice.setQuantity(1);
        outputInvoice.setSubtotal(new BigDecimal("1.00"));
        outputInvoice.setTax(new BigDecimal("1.00"));
        outputInvoice.setProcessingFee(new BigDecimal("1.00"));
        outputInvoice.setTotal(new BigDecimal("1.00"));
        outputInvoice.setInvoiceID(2);

        String outputJson = mapper.writeValueAsString(outputInvoice);

        mockMvc.perform(get("/invoice/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

}
