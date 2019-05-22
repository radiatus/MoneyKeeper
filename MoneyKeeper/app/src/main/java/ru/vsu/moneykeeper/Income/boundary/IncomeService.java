package ru.vsu.moneykeeper.Income.boundary;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.vsu.moneykeeper.Income.entity.Income;
import ru.vsu.moneykeeper.dao.IncomeDAO;

public class IncomeService {
    private IncomeDAO dao;

    public IncomeService(Context context) {
        dao = new IncomeDAO(context);
    }

    public List<Income> getAll(){
        /*List<Income> result = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = format.parse("15-04-2019");
            result.add(new Income(1L, "Взял взаймы", 1000F, date));
            date = format.parse("01-05-2019");
            result.add(new Income(1L, "Зарплата", 10000F, date));
        } catch (ParseException e){
        }
        return result;*/
        return dao.findAll();
    }

    public Income get(Long id){
        return dao.findById(id);
    }

    public List<Income> getLast(Date lastTime){
        return null;
    }

    public List<Income> getInInterval(Date startTime, Date endTime){
        return null;
    }

    public void delete(Long id){
        dao.delete(id);
    }

    public void add(Income income){
        dao.insert(income);
    }

    public void update(Income income){

    }
}
