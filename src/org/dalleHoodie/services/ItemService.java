package org.dalleHoodie.services;

import org.dalleHoodie.ApplicationContext;
import org.dalleHoodie.IService;
import org.dalleHoodie.model.Item;
import org.dalleHoodie.repository.ItemsRepository;

public class ItemService implements IService {
    private ApplicationContext applicationContext;
    private ItemsRepository itemsRepository;

    public ItemService(ApplicationContext applicationContext,
                           ItemsRepository itemsRepository) {
        this.setApplicationContext(applicationContext);
        this.itemsRepository = itemsRepository;
    }
    @Override
    public String perform(String[] param) {
        if (param.length != 1)
            return "Error (Number of parameters)\n";
        final int POISON = -666;
        try {
            int itemId = Integer.parseInt(param[0]);
            Item item = itemsRepository.getItem(itemId);
            if (item == null)
                return "No item with such Id\n";
            String out =    "Name: " + item.getItemName() + "\n" +
                            "Id: " + item.getItemId() + "\n" +
                            "ColorId: " + item.getColorId() + "\n" +
                            "CategoryId: " + item.getCategoryId() + "\n";
            return out;
        } catch (NumberFormatException e) {
            return "Error (Unknown parameter)\n";
        }
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
