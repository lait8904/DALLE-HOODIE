package org.dalleHoodie.services;

import org.dalleHoodie.IService;
import org.dalleHoodie.model.Category;
import org.dalleHoodie.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CategoryService implements IService {

    public List<Category> categories = new ArrayList<Category>();
    public List<Item> items = new ArrayList<Item>();

    public CategoryService() {
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

        //-------------------------------------//
        //Добавляем предметы
        Item item1 = new Item();
        item1.setItemId(1);
        item1.setItemName("black hoodie");
        item1.setCategoryId(1);
        items.add(item1);

        Item item2 = new Item();
        item2.setItemId(2);
        item2.setItemName("red hoodie");
        item2.setCategoryId(1);
        items.add(item2);

        Item item3 = new Item();
        item3.setItemId(3);
        item3.setItemName("black t-short");
        item3.setCategoryId(2);
        items.add(item3);
    }

    @Override
    public String perform(String[] param) {
        int categoryId = -666;
        if (param.length != 1)
            return "Error";
        for (Category category : categories)
            if (category.getCategoryName().equals(param[0])) {
                categoryId = category.getCategoryId();
                break;
            }
        if (categoryId == -666)
            return "No such category\n";
        String out = "";
        for (Item item : items)
            if (item.getCategoryId() == categoryId)
                out += "\"" + item.getItemName() + "\", ";

        if (out.length() > 0) {
            out = out.substring(0, out.length() - 2) + "\n";
            return out;
        }

        return "No items\n";
    }
}
