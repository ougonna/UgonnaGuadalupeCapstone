package com.company.UgonnaGuadalupeCapstone.dao;


import com.company.UgonnaGuadalupeCapstone.model.Console;
import com.company.UgonnaGuadalupeCapstone.model.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TaxDaoJdbcTemplateImp  implements  TaxDao{

    private JdbcTemplate _jdbcTemplate;
    private static final String SELECT_TAX =
            "select rate from sales_tax_rate where state = ?";

//    private static final String SELECT_ALL_TAX =
//            "select rate from sales_tax_rate where state = ?";

    @Autowired
    public TaxDaoJdbcTemplateImp(JdbcTemplate jdbcTemplate) {
        this._jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal getTax(String state) {
        return _jdbcTemplate.query(SELECT_TAX, this::extractData, state);
    }

    @Override
    public void setTax(String state, double rate) {

    }

    private BigDecimal extractData(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return resultSet.getBigDecimal("rate");
    }

    //row mapper
    private Tax mapRowToConsole(ResultSet rs, int rowNum) throws SQLException {
        Tax tax = new Tax();
        tax.set_state(rs.getString("state"));
        tax.set_rate(rs.getDouble("rate"));
        return tax;

    }
}
