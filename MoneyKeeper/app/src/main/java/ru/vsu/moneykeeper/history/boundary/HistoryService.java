package ru.vsu.moneykeeper.history.boundary;

import java.util.Date;
import java.util.List;

import ru.vsu.moneykeeper.Income.boundary.IncomeService;
import ru.vsu.moneykeeper.Income.entity.Income;
import ru.vsu.moneykeeper.category.boundary.CategoryService;
import ru.vsu.moneykeeper.expense.boundary.ExpenseService;
import ru.vsu.moneykeeper.history.entity.HistoryItem;

public class HistoryService {
    private IncomeService incomeService;
    private ExpenseService expenseService;
    private CategoryService categoryService;

    public HistoryService() {
        incomeService = new IncomeService();
        expenseService = new ExpenseService();
        categoryService = new CategoryService();
    }

    public List<HistoryItem> getAll(){
        return null;
    }

    public List<HistoryItem> getLast(Date lastTime){
        return null;
    }

    public List<HistoryItem> getInInterval(Date startTime, Date endTime){
        return null;
    }

}
