package com.company.UgonnaGuadalupeCapstone.dao;


import com.company.UgonnaGuadalupeCapstone.model.Processing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.math.BigDecimal;

//if it is not found
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessingDaoTest {

    @Autowired
    ProcessingDao processingDao;

    @Test
    public void getProcessingFee() {
        BigDecimal Fee = processingDao.getProcessingFee("Console");
        assertEquals(new BigDecimal("14.99"), Fee);

    }

}
