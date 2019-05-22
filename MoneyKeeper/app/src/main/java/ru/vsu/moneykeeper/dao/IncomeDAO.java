package ru.vsu.moneykeeper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.vsu.moneykeeper.Income.entity.Income;

public class IncomeDAO extends DAO<Income>  {
    public IncomeDAO(Context context) {
        super(context);
        tableName = "income";
    }

    @Override
    public List<Income> findAll() {
        List<Income> result = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("income", null, null, null, null, null, null);

        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int sumColIndex = c.getColumnIndex("sum");
            int nameColIndex = c.getColumnIndex("name");
            int dateColIndex = c.getColumnIndex("date");

            do {

                Long id = c.getLong(idColIndex);
                Float sum = c.getFloat(sumColIndex);
                String name = c.getString(nameColIndex);
                Date date = new Date(c.getLong(dateColIndex));

                Income newEntity = new Income(id, name, sum, date);

                result.add(newEntity);

            } while (c.moveToNext());
        }

        c.close();
        return result;
    }

    @Override
    public Income findById(Long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(String.format("SELECT * FROM expense WHERE id = %s", id),null);

        Income result = new Income();
        result.setId(id);

        if (c.moveToFirst()) {
            int sumColIndex = c.getColumnIndex("sum");
            int nameColIndex = c.getColumnIndex("name");
            int dateColIndex = c.getColumnIndex("date");

            result.setValue(c.getFloat(sumColIndex));
            result.setName(c.getString(nameColIndex));
            result.setDate(new Date(c.getLong(dateColIndex)));
        }

        c.close();
        return result;
    }

    @Override
    public void insert(Income newEntity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("sum", newEntity.getValue());
        cv.put("name", newEntity.getName());
        cv.put("date", newEntity.getDate().getTime());

        long rowID = db.insert("income", null, cv);
    }

    @Override
    public void update(Long id, Income changedEntity) {

    }

    @Override
    public void delete(Long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("income", "id = " + id, null);
    }
}
