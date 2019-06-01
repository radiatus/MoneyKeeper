package ru.vsu.moneykeeper.category.boundary;

import android.content.Context;

import java.util.List;

import ru.vsu.moneykeeper.category.entity.Category;
import ru.vsu.moneykeeper.dao.CategoryDAO;
import ru.vsu.moneykeeper.expense.boundary.ExpenseService;
import ru.vsu.moneykeeper.expense.entity.Expense;

public class CategoryService {
    private CategoryDAO dao;
    private ExpenseService expenseService;

    public CategoryService(Context context) {
        expenseService = new ExpenseService(context, this);
        dao = new CategoryDAO(context);
    }

    public List<Category> getAll(){
        return dao.findAll();
    }

    public Category get(Long id){
        return dao.findById(id);
    }

    public void delete(Long id){
        List<Expense> expenses = expenseService.getAll();
        for (Expense expense : expenses) {
            if (expense.getCategory().getId().equals(id))
                expenseService.delete(expense.getId());
        }
        dao.delete(id);
    }

    public void add(Category category){
        dao.insert(category);
    }

    public void update(Category category){
        dao.update(category.getId(), category);
    }
}
