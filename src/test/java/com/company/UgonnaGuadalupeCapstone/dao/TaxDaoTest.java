package com.company.UgonnaGuadalupeCapstone.dao;

import com.company.UgonnaGuadalupeCapstone.model.Console;
import com.company.UgonnaGuadalupeCapstone.model.Games;
import com.company.UgonnaGuadalupeCapstone.model.Tshirt;
import com.company.UgonnaGuadalupeCapstone.model.Tax;
import org.junit.Before;
import org.junit.Test;
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
