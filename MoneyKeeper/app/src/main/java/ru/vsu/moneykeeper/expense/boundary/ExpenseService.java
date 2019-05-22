package ru.vsu.moneykeeper.expense.boundary;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.vsu.moneykeeper.category.boundary.CategoryService;
import ru.vsu.moneykeeper.category.entity.Category;
import ru.vsu.moneykeeper.dao.ExpenseDAO;
import ru.vsu.moneykeeper.expense.entity.Expense;

public class ExpenseService {
    private ExpenseDAO dao;
    private CategoryService categoryService;

    public ExpenseService(Context context) {
        dao = new ExpenseDAO(context);
        categoryService = new CategoryService(context);
    }

    public List<Expense> getAll(){
        /*List<Expense> result = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = format.parse("02-05-2019");
            result.add(new Expense(1L, "Отдал долг", 1000F, date,
                    new Category(0L, "Общие расходы", null)));
            date = format.parse("15-04-2019");
            result.add(new Expense(2L, "Поел в кафе", 500F, date,
                    new Category(1L, "Еда", null)));
            date = format.parse("04-05-2019");
            result.add(new Expense(3L, "Туфли", 1500F, date,
                    new Category(2L, "Одежда", null)));
        } catch (ParseException e){
        }
        return result;*/
        List<Expense> result = dao.findAll();

        for (Expense expense: result){
            expense.setCategory(categoryService.get(expense.getCategory().getId()));
        }

        return result;//dao.findAll();
    }

    public Expense get(Long id){
        return dao.findById(id);
    }

    public List<Expense> getLast(Date lastTime){
        return null;
    }

    public List<Expense> getInInterval(Date startTime, Date endTime){
        return null;
    }

    public void delete(Long id){
        dao.delete(id);
    }

    public void add(Expense expense){
        dao.insert(expense);
    }

    public void update(Expense expense){

    }

    public void changeCategory(Long expenseId, Category newCategory){

    }

}
