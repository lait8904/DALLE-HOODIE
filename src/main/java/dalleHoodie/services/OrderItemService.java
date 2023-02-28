package dalleHoodie.services;

import dalleHoodie.ApplicationContext;
import dalleHoodie.IService;
import dalleHoodie.model.Item;
import dalleHoodie.model.Order;
import dalleHoodie.model.User;
import dalleHoodie.repository.ItemsRepository;
import dalleHoodie.repository.OrdersRepository;

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

            List<Order> draftOrders = ordersRepository.getOrders(
                    user.getUserId(), OrdersRepository.ConditionList.DRAFT);
                Order draftOrder = null;
            if (draftOrders == null || draftOrders.size() == 0)
                draftOrder = ordersRepository.createOrder(user.getUserId());
            else
                draftOrder = draftOrders.get(0);
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
