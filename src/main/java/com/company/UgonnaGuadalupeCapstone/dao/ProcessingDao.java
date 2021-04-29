package com.company.UgonnaGuadalupeCapstone.dao;

import java.math.BigDecimal;

//processing fee
public interface ProcessingDao {
    BigDecimal getProcessingFee(String itemType);
}
