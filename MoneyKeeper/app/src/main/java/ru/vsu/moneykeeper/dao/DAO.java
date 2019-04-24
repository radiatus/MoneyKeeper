package ru.vsu.moneykeeper.dao;

import java.sql.ResultSet;
import java.util.List;

public abstract class DAO<T> {
    protected String tableName;
    protected List<String> attribute_names;

    public DAO() {
    }

    public List<T> findAll() {
        return null;
    }

    public T findById(Long id) {
        return null;
    }

    public void insert(T newEntity) {

    }

    public void update(Long id, T changedEntity) {

    }

    public void delete(Long id) {

    }


    private List<T> executeSelectQuery(String query)
    {
        return null;
    }

    private void executeUpdateQuery(String query)
    {

    }

    protected abstract List<T> convertFrom(ResultSet resultSet) throws Exception;
    protected abstract String getValuesForInsert(T entity);
    protected abstract String getValuesForUpdate(T entity);
}
