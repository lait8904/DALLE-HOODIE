package org.dalleHoodie.repository;

import org.dalleHoodie.model.Category;
import org.dalleHoodie.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CategoriesRepository {

    public List<Category> categories = new ArrayList<Category>();

    public CategoriesRepository() {
        //Добавляем категории
        Category cat1 = new Category();
        cat1.setCategoryId(1);
        cat1.setCategoryName("hoodie");
        categories.add(cat1);

        Category cat2 = new Category();
        cat2.setCategoryId(2);
        cat2.setCategoryName("t-short");
        categories.add(cat2);

        Category cat3 = new Category();
        cat3.setCategoryId(3);
        cat3.setCategoryName("backpack");
        categories.add(cat3);
    }

    public List<Category> getCategories() {
        return this.categories;
    }

}
