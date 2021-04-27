package com.company.UgonnaGuadalupeCapstone.dao;

import com.company.UgonnaGuadalupeCapstone.model.Console;
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

        //clean test db
        List<Tshirt> stshirt = tshirtDao.getAllTshirt();
        for (Tshirt s : stshirt){
            tshirtDao.deleteTshirt(s.getTshirtId());

    }
}
@Test
    public void addGetDeleteTshirt(){
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("");
        tshirt.setColor("");
//        tshirt.

}
}
