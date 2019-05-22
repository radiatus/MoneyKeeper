package ru.vsu.moneykeeper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.ResultSet;
import java.util.List;

public abstract class DAO<T>{
    protected String tableName;
    protected List<String> attribute_names;
    protected DBHelper dbHelper;

    protected class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // создаем таблицы с полями
            db.execSQL("create table category ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "constr float" + ");");

            db.execSQL("create table expense ("
                    + "id integer primary key autoincrement,"
                    + "categoryId integer,"
                    + "sum float,"
                    + "name text,"
                    + "date long" + ");");

            db.execSQL("create table income ("
                    + "id integer primary key autoincrement,"
                    + "sum float,"
                    + "name text,"
                    + "date long" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public DAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public abstract List<T> findAll();

    public abstract T findById(Long id);

    public abstract void insert(T newEntity);

    public abstract void update(Long id, T changedEntity);

    public abstract void delete(Long id);
}
