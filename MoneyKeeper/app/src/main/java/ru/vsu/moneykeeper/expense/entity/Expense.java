package ru.vsu.moneykeeper.expense.entity;

import java.util.Date;

import ru.vsu.moneykeeper.category.entity.Category;
import ru.vsu.moneykeeper.history.entity.HistoryItem;

public class Expense extends HistoryItem {
    private Category category;

    public Expense() {
    }

    public Expense(Long id, String name, Float value, Date date) {
        super(id, name, value, date);
    }

    public Expense(Long id, String name, Float value, Date date, Category category) {
        super(id, name, value, date);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
