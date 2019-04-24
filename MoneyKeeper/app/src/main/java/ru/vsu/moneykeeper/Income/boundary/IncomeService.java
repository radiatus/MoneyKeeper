package ru.vsu.moneykeeper.Income.boundary;

import java.util.Date;
import java.util.List;

import ru.vsu.moneykeeper.Income.entity.Income;
import ru.vsu.moneykeeper.dao.IncomeDAO;

public class IncomeService {
    private IncomeDAO dao;

    public IncomeService() {
        dao = new IncomeDAO();
    }

    public List<Income> getAll(){
        return null;
    }

    public Income get(Long Id){
        return null;
    }

    public List<Income> getLast(Date lastTime){
        return null;
    }

    public List<Income> getInInterval(Date startTime, Date endTime){
        return null;
    }

    public void delete(Long Id){

    }

    public void add(Income income){

    }

    public void update(Income income){

    }
}
