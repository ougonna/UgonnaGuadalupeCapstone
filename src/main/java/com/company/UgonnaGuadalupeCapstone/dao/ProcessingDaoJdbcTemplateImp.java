package com.company.UgonnaGuadalupeCapstone.dao;

//processingFee
import com.company.UgonnaGuadalupeCapstone.model.Processing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ProcessingDaoJdbcTemplateImp implements ProcessingDao {

    private static final String SELECT_PROCESSING_SQL =
            "select * from processing_fee where product_type = ?";

    private JdbcTemplate _jdbcTemplate;
    @Autowired
    public ProcessingDaoJdbcTemplateImp(JdbcTemplate jdbcTemplate) {
        this._jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal getProcessingFee(String itemType) {
        return _jdbcTemplate.query(SELECT_PROCESSING_SQL, this::extractData, itemType);

    }
    private BigDecimal extractData(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return resultSet.getBigDecimal("fee");
    }


    //row mapper
    private Processing mapRowToGame(ResultSet rs, int rowNum) throws SQLException {
        Processing processing = new Processing();
        processing.setProductType(rs.getString("product_type"));
        processing.setFee(rs.getDouble("fee"));
        return processing;

    }

}
