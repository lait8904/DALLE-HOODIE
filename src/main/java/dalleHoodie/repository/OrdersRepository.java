package main.java.dalleHoodie.repository;

import main.java.dalleHoodie.model.Item;
import main.java.dalleHoodie.model.Order;
import main.java.dalleHoodie.model.OrderItem;
import main.java.dalleHoodie.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepository extends DataBase{

    public static class ConditionList {
        public static final String DRAFT = "draft";
        public static final String PROCESSED = "processed";
        public static final String DELIVERED = "delivered";
    }

    public enum Constants {
        SUCCESS,
        NO_USER,
        NO_ITEM,
        NO_ORDER,
    }



    public Order getOrder(int orderId) {
        for (Order order : orders)
            if (order.getOrderId() == orderId)
                return order;
        return null;
    }

    public Order createOrder(int userId) {
        Constants err = Constants.NO_USER;
        for (User user : users)
            if (user.getUserId() == userId) {
                err = Constants.SUCCESS;
                break;
            }
        if (err != Constants.SUCCESS)
            return null;

        Order order = new Order();
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setUserId(userId);
        order.setCondition(ConditionList.DRAFT);
        orders.add(order);
        return order;
    }

    public List<Order> getOrders(int userId) {
        List<Order> outOrders = new ArrayList<Order>();
        for (Order order : orders)
            if (order.getUserId() == userId)
                outOrders.add(order);
        return outOrders;
    }

    public List<Integer> getItemIds(int orderId) {
        List<Integer> outItemIds = new ArrayList<Integer>();
        for (OrderItem orderItem : orderItems)
            if (orderItem.getOrderId() == orderId)
                outItemIds.add(orderItem.getItemId());
        return outItemIds;
    }

    public OrderItem addItem(int orderId, int itemId) {
        Constants err = Constants.NO_ORDER;
        for (Order order : orders)
            if (order.getOrderId() == orderId) {
                err = Constants.SUCCESS;
                break;
            }
        if (err != Constants.SUCCESS)
            return null;

        err = Constants.NO_ITEM;
        for (Item item : items)
            if (item.getItemId() == itemId) {
                err = Constants.SUCCESS;
                break;
            }
        if (err != Constants.SUCCESS)
            return null;

        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(itemId);
        orderItem.setOrderId(orderId);
        orderItems.add(orderItem);
        return orderItem;
    }

}
