package org.dalleHoodie.services;

import org.dalleHoodie.IService;
import org.dalleHoodie.model.Category;

import java.util.List;
import java.util.ArrayList;

public class CategoriesService implements IService {
    public List<Category> categories = new ArrayList<Category>();

    //Добавляем объекты в список
    public CategoriesService() {
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

    @Override
    public String perform(String[] param) {
        String out = "";

        if (param.length != 0)
            return "Error\n";

        for (Category category : categories)
            out += "\"" + category.getCategoryName() + "\", ";

        if (categories.size() > 0) {
            out = out.substring(0, out.length() - 2) + "\n";
            return out;
        }

        return "No categories\n";
    }
}
