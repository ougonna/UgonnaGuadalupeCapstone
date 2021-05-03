package com.company.UgonnaGuadalupeCapstone.controller;


import com.company.UgonnaGuadalupeCapstone.dao.GamesDao;
import com.company.UgonnaGuadalupeCapstone.model.Games;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(GamesController.class)
@AutoConfigureMockMvc(addFilters = false)
//@WithMockUser(username = "me", roles={"ADMIN"})
public class GamesControllerTest {

    //wiring in the MVC object

    @Autowired
    private MockMvc mockMvc;


    //objectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    GamesDao gamesDao;


    @Test
    public void shouldReturnAllGames() throws Exception {

        //convert Java object to JSON
        String outputJson = mapper.writeValueAsString(gamesDao.getAllGames());

        //ACT
        mockMvc.perform(get("/games"))      //perform the get request
                .andDo(print())             // print results to console
                .andExpect(status().isOk()); //ASSERT (status code is 200)
    }

    //testing POST
    @Test
    public void shouldReturnNewGameOnPostRequest() throws Exception {

        //arrange
        Games inputGame = new Games();
        inputGame.setTitle("Game Title");
        inputGame.setEsrbRating("4.55");
        inputGame.setDescription("This is a test game");
        inputGame.setPrice(new BigDecimal("3.44"));
        inputGame.setStudio("studio test");
        inputGame.setQuantity(50);

        //convert Java object to JSON
        String inputJson = mapper.writeValueAsString(inputGame);

        Games outputGame = new Games();
        outputGame.setTitle("Game Title");
        outputGame.setEsrbRating("4.55");
        outputGame.setDescription("This is a test game");
        outputGame.setPrice(new BigDecimal("3.44"));
        outputGame.setStudio("studio test");
        outputGame.setQuantity(50);
        outputGame.setGameId(3);

        String outputJson = mapper.writeValueAsString(outputGame);

        //Act
        mockMvc.perform(
                post("/games")                      //perform the POST request
                    .content(inputJson)             // set the request body
                    .contentType(MediaType.APPLICATION_JSON)  //tell the server it is in JSON format
        )
                .andDo(print())                         // print results to console
                .andExpect(status().isCreated());          // ASSERT (status code is 201)

    }

    //testing GET by id
    @Test
    public void shouldReturnGameById() throws Exception {

        Games outputGame = new Games();
        outputGame.setTitle("Game Title");
        outputGame.setEsrbRating("4.55");
        outputGame.setDescription("This is a test game");
        outputGame.setPrice(new BigDecimal("3.44"));
        outputGame.setStudio("studio test");
        outputGame.setQuantity(50);
        outputGame.setGameId(3);

        String outputJson = mapper.writeValueAsString(outputGame);

        mockMvc.perform(get("/games/3"))
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(content().json(outputJson));
    }

    //testing GET by studio
    @Test
    public void shouldReturnGameByStudio() throws Exception {

        Games outputGame = new Games();
        outputGame.setTitle("Game Title");
        outputGame.setEsrbRating("4.55");
        outputGame.setDescription("This is a test game");
        outputGame.setPrice(new BigDecimal("3.44"));
        outputGame.setStudio("studioTest");
        outputGame.setQuantity(50);
        outputGame.setGameId(3);

        String outputJson = mapper.writeValueAsString(outputGame);

        mockMvc.perform(get("/games/studio/studioTest"))
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(content().json(outputJson));
    }

    //testing GET by ESRB
    @Test
    public void shouldReturnGameByESRB() throws Exception {

        Games outputGame = new Games();
        outputGame.setTitle("Game Title");
        outputGame.setEsrbRating("4.55");
        outputGame.setDescription("This is a test game");
        outputGame.setPrice(new BigDecimal("3.44"));
        outputGame.setStudio("studioTest");
        outputGame.setQuantity(50);
        outputGame.setGameId(3);

        String outputJson = mapper.writeValueAsString(outputGame);

        mockMvc.perform(get("/games/esrb/4.55"))
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(content().json(outputJson));
    }

    //testing GET by title
    @Test
    public void shouldReturnGameByTitle() throws Exception {

        Games outputGame = new Games();
        outputGame.setTitle("GameTitle");
        outputGame.setEsrbRating("4.55");
        outputGame.setDescription("This is a test game");
        outputGame.setPrice(new BigDecimal("3.44"));
        outputGame.setStudio("studioTest");
        outputGame.setQuantity(50);
        outputGame.setGameId(3);

        String outputJson = mapper.writeValueAsString(outputGame);

        mockMvc.perform(get("/games/title/GameTitle"))
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(content().json(outputJson));
    }

    //testing put
    @Test
    public void shouldUpdateById() throws Exception {

        Games inputGame = new Games();
        inputGame.setTitle("Game Title");
        inputGame.setEsrbRating("4.55");
        inputGame.setDescription("This is a test game");
        inputGame.setPrice(new BigDecimal("3.44"));
        inputGame.setStudio("studio test");
        inputGame.setQuantity(50);
        inputGame.setGameId(3);

        String inputJson = mapper.writeValueAsString(inputGame);

        mockMvc.perform(
                put("/games")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

    }

    // Testing DELETE
    @Test
    public void shouldDeleteById() throws Exception {


        mockMvc.perform(delete("/games/5"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
