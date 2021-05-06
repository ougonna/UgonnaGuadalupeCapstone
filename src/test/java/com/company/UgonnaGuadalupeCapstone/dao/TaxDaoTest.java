package com.company.UgonnaGuadalupeCapstone.dao;



import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxDaoTest {

    //if it is not found
    @Autowired
    TaxDao taxDao;


    @Test
    public void getTax() {
        BigDecimal tax = taxDao.getTax("AL");
        assertEquals(new BigDecimal("0.05"), tax);
    }

}
