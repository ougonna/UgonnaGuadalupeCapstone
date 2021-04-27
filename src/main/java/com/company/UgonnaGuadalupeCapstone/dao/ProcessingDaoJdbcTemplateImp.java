package com.company.UgonnaGuadalupeCapstone.dao;

//processingFee

import org.springframework.stereotype.Service;

@Service
public class ProcessingDaoJdbcTemplateImp implements ProcessingDao {
    @Override
    public double getProcessingFee(String itemType) {
        return 0;
    }
}
