package com.company.UgonnaGuadalupeCapstone.dao;

import com.company.UgonnaGuadalupeCapstone.model.Console;
import com.company.UgonnaGuadalupeCapstone.model.Games;
import com.company.UgonnaGuadalupeCapstone.model.Tshirt;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TshirtDaoTest {

    @Autowired
    TshirtDao tshirtDao;

    @Before
    public void setUp() throws Exception {

        List<Tshirt> stshirt = tshirtDao.getAllTshirt();
        for (Tshirt s : stshirt){
            tshirtDao.deleteTshirt(s.getTshirtId());

    }
}
@Test
    public void addGetDeleteTshirt(){
        Tshirt tshirts = new Tshirt();
        tshirts.setSize("");
        tshirts.setColor("");
        tshirts.setDescription("");
        tshirts.setQuantity(5);
        tshirts.setPrice(new BigDecimal("14.50"));

        tshirts = tshirtDao.addTshirt(tshirts);
        Tshirt tshirts1 = tshirtDao.getTshirt(tshirts.getTshirtId());
        assertEquals(tshirts1, tshirts);


        tshirtDao.deleteTshirt(tshirts.getTshirtId());
        tshirts1 = tshirtDao.getTshirt(tshirts.getTshirtId());
        assertNull(tshirts1);
}


}
