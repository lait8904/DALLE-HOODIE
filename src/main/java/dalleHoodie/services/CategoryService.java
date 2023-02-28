package dalleHoodie.services;

import dalleHoodie.ApplicationContext;
import dalleHoodie.IService;
import dalleHoodie.model.Item;
import dalleHoodie.repository.ItemsRepository;

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
        try {
            int categoryId = Integer.parseInt(param[0]);
            List<Item> items = itemsRepository.getItems(categoryId);

            if (items == null || items.size() == 0) {
                return "No items in this category yet\n";
            }
            String out = "";
            for (Item item : items)
                out += "\"" + item.getItemName() + "\" {" + item.getItemId() + "}, ";
            out = out.substring(0, out.length() - 2) + "\n";

            return out;
        } catch (NumberFormatException e) {
            return "Error (Incorrect parameter)\n";
        }
    }
}
