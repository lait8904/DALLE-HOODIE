package main.java.dalleHoodie.services;

import main.java.dalleHoodie.ApplicationContext;
import main.java.dalleHoodie.IService;
import main.java.dalleHoodie.repository.CategoriesRepository;
import main.java.dalleHoodie.model.Category;

import java.util.List;

public class CategoriesService implements IService {
//    public List<Category> categories = new ArrayList<Category>();

    private ApplicationContext applicationContext;
    private CategoriesRepository categoriesRepository;

    //Добавляем объекты в список
    public CategoriesService(ApplicationContext applicationContext,
                             CategoriesRepository categoriesRepository) {
        this.applicationContext = applicationContext;
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
}
