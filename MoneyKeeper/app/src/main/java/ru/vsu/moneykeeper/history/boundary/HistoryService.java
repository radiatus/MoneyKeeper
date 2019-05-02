package ru.vsu.moneykeeper.history.boundary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import ru.vsu.moneykeeper.Income.boundary.IncomeService;
import ru.vsu.moneykeeper.Income.entity.Income;
import ru.vsu.moneykeeper.category.boundary.CategoryService;
import ru.vsu.moneykeeper.expense.boundary.ExpenseService;
import ru.vsu.moneykeeper.expense.entity.Expense;
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
        List<HistoryItem> result = new ArrayList<>();
        for (Income income: incomeService.getAll()) {
            result.add(income);
        }

        for (Expense expense: expenseService.getAll()) {
            result.add(expense);
        }

        Collections.sort(result, new Comparator<HistoryItem>() {
            @Override
            public int compare(HistoryItem o1, HistoryItem o2) {
                int val = o2.getDate().compareTo(o1.getDate());
                return val;
            }
        });

        return result;
    }

    public List<HistoryItem> getLast(Date lastTime){
        return null;
    }

    public List<HistoryItem> getInInterval(Date startTime, Date endTime){
        return null;
    }

}
