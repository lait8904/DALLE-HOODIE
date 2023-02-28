package dalleHoodie.services;

import dalleHoodie.ApplicationContext;
import dalleHoodie.IService;
import dalleHoodie.model.Item;
import dalleHoodie.model.Order;
import dalleHoodie.model.User;
import dalleHoodie.repository.ItemsRepository;
import dalleHoodie.repository.OrdersRepository;

import java.util.ArrayList;
import java.util.List;

public class OrdersService implements IService {

    private ApplicationContext applicationContext;
    private OrdersRepository ordersRepository;
    private ItemsRepository itemsRepository;

    public OrdersService(ApplicationContext applicationContext,
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
        String out = "";
        User user = applicationContext.getUser();
        if (user == null)
            return "Authorize firstly!\n";
        List<Order> usersOrders = ordersRepository.getOrders(user.getUserId(), null);

        if (usersOrders == null || usersOrders.size() == 0)
            usersOrders = List.of(ordersRepository.createOrder(user.getUserId()));

        for (Order order : usersOrders) {
            if (order == null) {
                out += null + "\n";
                continue;
            }

            out += order.getCondition() + " order from " + order.getOrderDate() + "\n";

            List<Integer> itemIds = ordersRepository.getItemIds(order.getOrderId());
            if (itemIds == null || itemIds.size() == 0) {
                out += "\tNo items in order\n";
                itemIds = new ArrayList<>();
            }
            for (Integer itemId : itemIds) {
                Item item = itemsRepository.getItem(itemId);
                if (item == null)
                    return "Error (Non-existent item in order from " + order.getOrderDate() + "\n;";
                out += "\t" + item.getItemName() + "(" + item.getItemId() + ")\n";
            }
        }
        return out;
    }
}
