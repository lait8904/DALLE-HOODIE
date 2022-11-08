package org.dalleHoodie.services;

import org.dalleHoodie.ApplicationContext;
import org.dalleHoodie.IService;
import org.dalleHoodie.model.Category;
import org.dalleHoodie.repository.CategoriesRepository;

import java.util.List;
import java.util.ArrayList;

public class CategoriesService implements IService {
//    public List<Category> categories = new ArrayList<Category>();

    private ApplicationContext applicationContext;
    private CategoriesRepository categoriesRepository;

    //Добавляем объекты в список
    public CategoriesService(ApplicationContext applicationContext,
                             CategoriesRepository categoriesRepository) {
        this.setApplicationContext(applicationContext);
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public String perform(String[] param) {
        if (param.length != 0)
            return "Error (number of parameters)\n";

        List<Category> categories = categoriesRepository.getCategories();
        if (categories.size() == 0)
            return "No such categories yet";

        String out = "";

        for (Category category : categories)
            out += "\"" + category.getCategoryName() + "\" {" + category.getCategoryId() + "}, ";
        out = out.substring(0, out.length() - 2) + "\n";

        return out;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
