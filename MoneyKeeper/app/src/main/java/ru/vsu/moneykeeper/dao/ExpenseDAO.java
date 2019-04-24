package ru.vsu.moneykeeper.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ru.vsu.moneykeeper.expense.entity.Expense;

public class ExpenseDAO extends DAO<Expense> {
    public ExpenseDAO() {
        super();
        tableName = "Dishes";
        attribute_names = new ArrayList<>();
        attribute_names.add("name");
        attribute_names.add("name");
        attribute_names.add("category_id");
    }

    @Override
    protected List<Expense> convertFrom(ResultSet resultSet) throws Exception {
        return null;
    }

    @Override
    protected String getValuesForInsert(Expense entity) {
        return null;
    }

    @Override
    protected String getValuesForUpdate(Expense entity) {
        return null;
    }
}
