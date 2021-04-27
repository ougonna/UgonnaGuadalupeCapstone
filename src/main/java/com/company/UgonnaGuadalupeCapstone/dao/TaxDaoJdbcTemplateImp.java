package com.company.UgonnaGuadalupeCapstone.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class TaxDaoJdbcTemplateImp  implements  TaxDao{

    private JdbcTemplate _jdbcTemplate;
    private static final String SELECT_TAX =
            "select rate from sales_tax_rate where state = ?";
    private static final String SET_TAX =
            "select rate from sales_tax_rate where state = ?";
    @Autowired
    public TaxDaoJdbcTemplateImp(JdbcTemplate jdbcTemplate) {
        this._jdbcTemplate = jdbcTemplate;
    }

    @Override
    public double getTax(String state) {
        return _jdbcTemplate.query(SELECT_TAX, this::extractData, state);
    }

    @Override
    public void setTax(String state, double rate) {

    }

    private double extractData(ResultSet resultSet) throws SQLException {
        return resultSet.getDouble("rate");
    }
}
