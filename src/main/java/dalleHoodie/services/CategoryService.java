package main.java.dalleHoodie.services;

import main.java.dalleHoodie.ApplicationContext;
import main.java.dalleHoodie.IService;
import main.java.dalleHoodie.model.Item;
import main.java.dalleHoodie.repository.ItemsRepository;

import java.util.List;

public class CategoryService implements IService {

    private ApplicationContext applicationContext;
    private ItemsRepository itemsRepository;

    public CategoryService(ApplicationContext applicationContext,
                           ItemsRepository itemsRepository) {
        this.applicationContext = applicationContext;
        this.itemsRepository = itemsRepository;
    }

    @Override
    public String perform(String[] param) {
        if (param.length != 1) {
            return "Error (Number of parameters)\n";
        }
        List<Item> items = itemsRepository.getItems(param[0]);


        if (items.size() == 0) {
            return "No items in this category yet\n";
        }
        String out = "";
        for (Item item : items)
            out += "\"" + item.getItemName() + "\" {" + item.getItemId() + "}, ";
        out = out.substring(0, out.length() - 2) + "\n";

        return out;
    }
}
