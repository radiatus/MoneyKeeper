package ru.vsu.moneykeeper.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ru.vsu.moneykeeper.category.entity.Category;

public class CategoryDAO extends DAO<Category> {
    public CategoryDAO() {
        super();
        tableName = "Category";
        attribute_names = new ArrayList<>();
        attribute_names.add("name");
        attribute_names.add("constraint");
    }

    @Override
    protected List<Category> convertFrom(ResultSet resultSet) throws Exception {
        return null;
    }

    @Override
    protected String getValuesForInsert(Category entity) {
        return null;
    }

    @Override
    protected String getValuesForUpdate(Category entity) {
        return null;
    }
}
