package main.java.dalleHoodie.services;

import main.java.dalleHoodie.ApplicationContext;
import main.java.dalleHoodie.IService;
import main.java.dalleHoodie.model.Item;
import main.java.dalleHoodie.model.Order;
import main.java.dalleHoodie.model.User;
import main.java.dalleHoodie.repository.ItemsRepository;
import main.java.dalleHoodie.repository.OrdersRepository;

import java.util.List;

public class OrderItemService implements IService {
    private ApplicationContext applicationContext;
    private OrdersRepository ordersRepository;
    private ItemsRepository itemsRepository;

    public OrderItemService(ApplicationContext applicationContext,
                            OrdersRepository ordersRepository,
                            ItemsRepository itemsRepository) {
        this.ordersRepository = ordersRepository;
        this.applicationContext = applicationContext;
        this.itemsRepository = itemsRepository;
    }

    @Override
    public String perform(String[] param) {
        if (param.length != 1)
            return "Error (Number of parameters)\n";

        User user = applicationContext.getUser();
        if (user == null)
            return "Authorize firstly!\n";

        try {
            int itemId = Integer.parseInt(param[0]);
            Item item = itemsRepository.getItem(itemId);
            if (item == null)
                return "No item with such Id\n";

            List<Order> UsersOrders = ordersRepository.getOrders(user.getUserId());
            Order draftOrder = null;
            for (Order order : UsersOrders)
                if (order.getCondition().equals(OrdersRepository.ConditionList.DRAFT)) {
                    draftOrder = order;
                    break;
                }
            if (draftOrder == null)
                draftOrder = ordersRepository.createOrder(user.getUserId());
            if (draftOrder == null)
                return "Error (Authorize?)\n";

            if (ordersRepository.addItem(draftOrder.getOrderId(), itemId) == null)
                return "Error (Can't add)\n";
            return "Success!\n";

        } catch (NumberFormatException e) {
            return "Error (Incorrect parameter)\n";
        }
    }
}
