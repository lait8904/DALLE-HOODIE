package main.java.dalleHoodie.repository;

import main.java.dalleHoodie.model.Category;

import java.util.List;

public class CategoriesRepository extends DataBase{

    public CategoriesRepository() {
        super();
    }

    public List<Category> getCategories() {
        return categories;
    }

}
