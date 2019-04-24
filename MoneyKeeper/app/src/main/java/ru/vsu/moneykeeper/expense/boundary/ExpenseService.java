package ru.vsu.moneykeeper.expense.boundary;

import java.util.Date;
import java.util.List;

import ru.vsu.moneykeeper.category.entity.Category;
import ru.vsu.moneykeeper.dao.ExpenseDAO;
import ru.vsu.moneykeeper.expense.entity.Expense;

public class ExpenseService {
    private ExpenseDAO dao;

    public ExpenseService() {
        dao = new ExpenseDAO();
    }

    public List<Expense> getAll(){
        return null;
    }

    public Expense get(Long Id){
        return null;
    }

    public List<Expense> getLast(Date lastTime){
        return null;
    }

    public List<Expense> getInInterval(Date startTime, Date endTime){
        return null;
    }

    public void delete(Long Id){

    }

    public void add(Expense income){

    }

    public void update(Expense income){

    }

    public void changeCategory(Long expenseId, Category newCategory){

    }

}
