package ru.vsu.moneykeeper.category.boundary;

import java.util.List;

import ru.vsu.moneykeeper.category.entity.Category;
import ru.vsu.moneykeeper.dao.CategoryDAO;

public class CategoryService {
    private CategoryDAO dao;

    public CategoryService() {
        dao = new CategoryDAO();
    }

    public List<Category> getAll(){
        return null;
    }

    public Category get(Long Id){
        return null;
    }



    public void delete(Long Id){
    }

    public void add(Category income){
    }

    public void update(Category income){

    }
}
