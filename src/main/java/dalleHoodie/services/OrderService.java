package main.java.dalleHoodie.services;

import main.java.dalleHoodie.ApplicationContext;
import main.java.dalleHoodie.IService;
import main.java.dalleHoodie.model.Item;
import main.java.dalleHoodie.model.Order;
import main.java.dalleHoodie.model.User;
import main.java.dalleHoodie.repository.ItemsRepository;
import main.java.dalleHoodie.repository.OrdersRepository;

import java.util.List;

public class OrderService implements IService {

    private ApplicationContext applicationContext;
    private OrdersRepository ordersRepository;
    private ItemsRepository itemsRepository;

    public OrderService(ApplicationContext applicationContext,
                        OrdersRepository ordersRepository,
                        ItemsRepository itemsRepository) {
        this.applicationContext = applicationContext;
        this.ordersRepository = ordersRepository;
        this.itemsRepository = itemsRepository;
    }

    @Override
    public String perform(String[] param) {
        if (param.length != 0)
            return "Error (Number of parameters\n";
        String out = "Items in bag:\n";
        User user = applicationContext.getUser();
        if (user == null)
            return "Authorize firstly!\n";
        List<Order> draftOrders = ordersRepository.getOrders(
                    user.getUserId(), OrdersRepository.ConditionList.DRAFT);
        Order draftOrder = null;
        if (draftOrders.size() == 0)
            draftOrder = ordersRepository.createOrder(user.getUserId());
        else
            draftOrder = draftOrders.get(0);
        if (draftOrder == null)
            return "Error (Authorize?)\n";

        List<Integer> itemIds = ordersRepository.getItemIds(draftOrder.getOrderId());
        if (itemIds.size() == 0)
            return "No items in bag yet\n";
        for (Integer itemId : itemIds) {
            Item item = itemsRepository.getItem(itemId);
            out += "\t" + item.getItemName() + "\n";
        }
        return out;

    }
}
