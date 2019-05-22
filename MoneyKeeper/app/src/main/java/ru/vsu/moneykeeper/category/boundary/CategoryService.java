package ru.vsu.moneykeeper.category.boundary;

import android.content.Context;

import java.util.List;

import ru.vsu.moneykeeper.category.entity.Category;
import ru.vsu.moneykeeper.dao.CategoryDAO;

public class CategoryService {
    private CategoryDAO dao;

    public CategoryService(Context context) {
        dao = new CategoryDAO(context);
    }

    public List<Category> getAll(){
        return dao.findAll();
    }

    public Category get(Long id){
        return dao.findById(id);
    }

    public void delete(Long Id){
    }

    public void add(Category category){
        dao.insert(category);
    }

    public void update(Category category){

    }
}
