package main.java.dalleHoodie.repository;

import main.java.dalleHoodie.model.Item;
import main.java.dalleHoodie.model.Category;
import main.java.dalleHoodie.model.Order;
import main.java.dalleHoodie.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class ItemsRepository extends DataBase{

    public ItemsRepository() {
        super();
    }

    public List<Item> getItems(String categoryName) {
        List<Item> itemsInCategory = new ArrayList<Item>();
        final int POISON = -666;
        int categoryId = POISON;
        for (Category category : categories)
            if (category.getCategoryName().equals(categoryName)) {
                categoryId = category.getCategoryId();
                break;
            }

        if (categoryId == POISON)
            return itemsInCategory;

        for (Item item : items)
            if (item.getCategoryId() == categoryId)
                itemsInCategory.add(item);

        return itemsInCategory;
    }

    public Item getItem(int itemId) {
        for (Item item : items)
            if (item.getItemId() == itemId)
                return item;
        return null;
    }

}
