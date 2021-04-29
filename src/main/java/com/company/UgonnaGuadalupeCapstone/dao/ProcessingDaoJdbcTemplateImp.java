package com.company.UgonnaGuadalupeCapstone.dao;

//processingFee

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProcessingDaoJdbcTemplateImp implements ProcessingDao {
    @Override
    public BigDecimal getProcessingFee(String itemType) {
        return BigDecimal.valueOf(0);
    }
}
