package ru.vsu.moneykeeper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.vsu.moneykeeper.category.entity.Category;
import ru.vsu.moneykeeper.expense.entity.Expense;

public class ExpenseDAO extends DAO<Expense> {
    public ExpenseDAO(Context context) {
        super(context);
        tableName = "expense";
        attribute_names = new ArrayList<>();
        attribute_names.add("categoryId");
        attribute_names.add("sum");
        attribute_names.add("name");
        attribute_names.add("date");

    }

    @Override
    public List<Expense> findAll() {
        List<Expense> result = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("expense", null, null, null, null, null, null);

        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int categoryIdColIndex = c.getColumnIndex("categoryId");
            int sumColIndex = c.getColumnIndex("sum");
            int nameColIndex = c.getColumnIndex("name");
            int dateColIndex = c.getColumnIndex("date");

            do {

                Long id = c.getLong(idColIndex);
                Long categoryId = c.getLong(categoryIdColIndex);
                Float sum = c.getFloat(sumColIndex);
                String name = c.getString(nameColIndex);
                Date date = new Date(c.getLong(dateColIndex));

                Expense newEntity = new Expense(id, name, sum, date,
                        new Category(categoryId, "", 0F));

                result.add(newEntity);

            } while (c.moveToNext());
        }

        c.close();
        return result;
    }

    @Override
    public Expense findById(Long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(String.format("SELECT * FROM expense WHERE id = %s", id),null);

        Expense result = new Expense();
        result.setId(id);

        if (c.moveToFirst()) {
            int categoryIdColIndex = c.getColumnIndex("categoryId");
            int sumColIndex = c.getColumnIndex("sum");
            int nameColIndex = c.getColumnIndex("name");
            int dateColIndex = c.getColumnIndex("date");

            result.setCategory(new Category(c.getLong(categoryIdColIndex), null, null));
            result.setValue(c.getFloat(sumColIndex));
            result.setName(c.getString(nameColIndex));
            result.setDate(new Date(c.getLong(dateColIndex)));
        }

        c.close();
        return result;
    }

    @Override
    public void insert(Expense newEntity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("categoryId", newEntity.getCategory().getId());
        cv.put("sum", newEntity.getValue());
        cv.put("name", newEntity.getName());
        cv.put("date", newEntity.getDate().getTime());

        long rowID = db.insert("expense", null, cv);
    }

    @Override
    public void update(Long id, Expense changedEntity) {

    }

    @Override
    public void delete(Long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("expense", "id = " + id, null);
    }
}
