package ru.vsu.moneykeeper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ru.vsu.moneykeeper.category.entity.Category;

public class CategoryDAO extends DAO<Category> {

    public CategoryDAO(Context context) {
        super(context);
        tableName = "category";
        attribute_names = new ArrayList<>();
        attribute_names.add("name");
        attribute_names.add("constr");
    }

    @Override
    public List<Category> findAll() {
        List<Category> result = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("category", null, null, null, null, null, null);

        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int constraintColIndex = c.getColumnIndex("constr");
            do {

                Long id = c.getLong(idColIndex);
                String name = c.getString(nameColIndex);
                Float constraint = c.getFloat(constraintColIndex);
                Category newCategory = new Category(id, name, constraint);
                result.add(newCategory);

            } while (c.moveToNext());
        }

        c.close();
        return result;
    }

    @Override
    public Category findById(Long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(String.format("SELECT * FROM category WHERE id = %s", id),null);

        Category result = new Category();
        result.setId(id);
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int nameColIndex = c.getColumnIndex("name");
            int constraintColIndex = c.getColumnIndex("constr");
            result.setName(c.getString(nameColIndex));
            result.setConstraint(c.getFloat(constraintColIndex));
        }

        c.close();
        return result;
    }

    @Override
    public void insert(Category newEntity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", newEntity.getName());
        cv.put("constr", newEntity.getConstraint());
        // вставляем запись и получаем ее ID
        long rowID = db.insert("category", null, cv);
    }

    @Override
    public void update(Long id, Category changedEntity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", changedEntity.getName());
        cv.put("constr", changedEntity.getConstraint());
        // вставляем запись и получаем ее ID
        long rowID = db.update("category", cv,"id = ?",
                new String[] { id.toString() });
    }

    @Override
    public void delete(Long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        db.delete("category", "id = ?",
                new String[] { id.toString() });
    }
}
