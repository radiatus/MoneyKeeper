package ru.vsu.moneykeeper.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ru.vsu.moneykeeper.Income.entity.Income;

public class IncomeDAO extends DAO<Income>  {
    public IncomeDAO() {
        super();
        tableName = "Dishes";
        attribute_names = new ArrayList<>();
        attribute_names.add("name");
        attribute_names.add("date");
    }

    @Override
    protected List<Income> convertFrom(ResultSet resultSet) throws Exception {
        return null;
    }

    @Override
    protected String getValuesForInsert(Income entity) {
        return null;
    }

    @Override
    protected String getValuesForUpdate(Income entity) {
        return null;
    }
}
