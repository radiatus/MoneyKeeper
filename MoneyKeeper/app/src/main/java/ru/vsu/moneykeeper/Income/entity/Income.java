package ru.vsu.moneykeeper.Income.entity;

import java.util.Date;

import ru.vsu.moneykeeper.history.entity.HistoryItem;

public class Income extends HistoryItem {

    public Income() {
    }

    public Income(Long id, String name, Float value, Date date) {
        super(id, name, value, date);
    }

}
