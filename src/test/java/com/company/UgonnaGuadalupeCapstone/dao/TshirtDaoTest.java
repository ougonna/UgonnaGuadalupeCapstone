package com.company.UgonnaGuadalupeCapstone.dao;


import com.company.UgonnaGuadalupeCapstone.model.Tshirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TshirtDaoTest {

    @Autowired
    TshirtDao tshirtDao;

    @Before
    public void setUp() throws Exception {

        List<Tshirt> stshirt = tshirtDao.getAllTshirt();
        for (Tshirt s : stshirt) {
            tshirtDao.deleteTshirt(s.getTshirtId());
        }
    }

    @Test
    public void addGetDeleteTshirt() {
        Tshirt tshirts = new Tshirt();
        tshirts.setSize("small");
        tshirts.setColor("blue");
        tshirts.setDescription("something");
        tshirts.setQuantity(5);
        tshirts.setPrice(new BigDecimal("14.50"));

        tshirts = tshirtDao.addTshirt(tshirts);
        Tshirt tshirts1 = tshirtDao.getTshirt(tshirts.getTshirtId());
        assertEquals(tshirts1, tshirts);


        tshirtDao.deleteTshirt(tshirts.getTshirtId());
        tshirts1 = tshirtDao.getTshirt(tshirts.getTshirtId());
        assertNull(tshirts1);
    }

    @Test
    public void getAllTshirt() {

        Tshirt tshirt = new Tshirt();
        tshirt.setSize("small");
        tshirt.setColor("blue");
        tshirt.setDescription("something");
        tshirt.setQuantity(5);
        tshirt.setPrice(new BigDecimal("34.50"));

        tshirtDao.addTshirt(tshirt);

        Tshirt tshirt1 = new Tshirt();

        tshirt1.setSize("small");
        tshirt1.setColor("blue");
        tshirt1.setDescription("something");
        tshirt1.setQuantity(5);
        tshirt1.setPrice(new BigDecimal("34.10"));

        tshirtDao.addTshirt(tshirt1);

        List<Tshirt> tshirts = tshirtDao.getAllTshirt();

        assertEquals(2, tshirts.size());

    }

    @Test
    public void geTshirtByColor() {

        Tshirt tshirt = new Tshirt();
        tshirt.setSize("small");
        tshirt.setColor("blue");
        tshirt.setDescription("something");
        tshirt.setQuantity(5);
        tshirt.setPrice(new BigDecimal("34.50"));

        tshirtDao.addTshirt(tshirt);

        Tshirt tshirt1 = new Tshirt();

        tshirt1.setSize("small");
        tshirt1.setColor("blue");
        tshirt1.setDescription("something");
        tshirt1.setQuantity(5);
        tshirt1.setPrice(new BigDecimal("34.10"));

        tshirtDao.addTshirt(tshirt1);

        List<Tshirt> tshirtlist = tshirtDao.getTshirtByColor("blue");

        assertEquals(tshirtlist.size(), 2);


    }

    @Test
    public void updateTshirt() {

        Tshirt tshirt = new Tshirt();
        tshirt.setSize("small");
        tshirt.setColor("blue");
        tshirt.setDescription("something");
        tshirt.setQuantity(5);
        tshirt.setPrice(new BigDecimal("34.50"));


        tshirtDao.addTshirt(tshirt);

        tshirt.setQuantity(50);
        tshirt.setSize("Large");
        tshirtDao.updateTshirt(tshirt);

        Tshirt tshirt1 = tshirtDao.getTshirt(tshirt.getTshirtId());
        assertEquals(tshirt, tshirt1);
    }
}
