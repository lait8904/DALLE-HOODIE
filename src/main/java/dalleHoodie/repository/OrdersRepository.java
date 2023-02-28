package dalleHoodie.repository;

import dalleHoodie.DBClient;
import dalleHoodie.model.Item;
import dalleHoodie.model.Order;
import dalleHoodie.model.OrderItem;
import dalleHoodie.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepository{

    public static class ConditionList {
        public static final String DRAFT = "draft";
        public static final String PROCESSED = "processed";
        public static final String DELIVERED = "delivered";
    }

    private DBClient dbClient;

    public OrdersRepository(DBClient dbClient) {
        this.dbClient = dbClient;
    }


    /**
     * @param orderId
     * @return Order or null
     */
    public Order getOrder(int orderId) {
        String selectOrderSql = "select * from orders";
        Order order = dbClient.executeSelect(selectOrderSql, resultSet -> {
            Order order_ = new Order();
            this.setOrder(order_, resultSet);
            return order_;
        });
        return order;
    }

    private void setOrder(Order order, ResultSet resultSet) throws SQLException {
        order.setOrderId(resultSet.getInt("order_id"));
        order.setUserId(resultSet.getInt("user_id"));
        order.setCondition(resultSet.getString("condition"));
        order.setOrderDate(resultSet.getTimestamp("order_date"));
        order.setPrice(resultSet.getInt("price"));
    }


    public Order createOrder(int userId) {
        String selectUserSql = "select user_id from users";
        Boolean userExistence = dbClient.executeSelect(selectUserSql, resultSet -> true);
        if (userExistence == null)
            return null;
        //TODO add return description
        Order order = new Order();
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setUserId(userId);
        order.setCondition(ConditionList.DRAFT);

        String insertOrderSql = "insert into orders (user_id, condition, order_date, price) " +
                "values (?, ?, ?, ?) returning order_id";

        order = dbClient.executeUpdate(insertOrderSql, order,
                (preparedStatement, order_) -> {
                    preparedStatement.setInt(1, order_.getUserId());
                    preparedStatement.setString(2, order_.getCondition());
                    preparedStatement.setTimestamp(3, order_.getOrderDate());
                    preparedStatement.setInt(4, order_.getPrice());
                },
                (resultSet, order_) -> {
                    order_.setOrderId(resultSet.getInt("order_id"));
                    return order_;
                });
        return order;
    }

    /**
     * @param userId
     * @param condition like "draft", "processed", "delivered", etc
     * @return list of orders or null if they not exist
     */
    public List<Order> getOrders(int userId, String condition) {
        String additionalCriteriaSql = "";
        if (condition != null)
            additionalCriteriaSql += " and condition = '" + condition + "'";
        String selectOrdersSql = "select * from orders where user_id = "
                + userId + additionalCriteriaSql;
        //TODO all usages are duplicates
        List<Order> orders = dbClient.executeSelect(selectOrdersSql, resultSet -> {
            List<Order> orders_ = new ArrayList<>();
            do {
                Order order = new Order();
                this.setOrder(order, resultSet);
                orders_.add(order);
            } while (resultSet.next());
            return orders_;
        });

        return orders;
    }

    /**
     * @param orderId
     * @return List of itemId or null if there are no items in order
     */
    public List<Integer> getItemIds(int orderId) {
        String selectItemsSql = "select item_id from order_items where order_id  = "
        + orderId;
        List<Integer> itemIds = dbClient.executeSelect(selectItemsSql, resultSet -> {
            List<Integer> itemIds_ = new ArrayList<>();
            do {
                itemIds_.add(resultSet.getInt("item_id"));
            } while (resultSet.next());
            return itemIds_;
        });

        return itemIds;
    }

    public OrderItem addItem(int orderId, int itemId) {
        String selectOrderSql = "select order_id from orders";

        Boolean existenceSmth = dbClient.executeSelect(selectOrderSql, resultSet -> true);
        if (existenceSmth == null)
            return null;

        String selectItemSql = "select item_id from items";

        existenceSmth = dbClient.executeSelect(selectItemSql, resultSet -> true);
        if (existenceSmth == null)
                return null;
        //TODO add return description
        String insertOrderItemsSql = "insert into order_items (order_id, item_id) " +
                "values (?, ?)";

        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(itemId);
        orderItem.setOrderId(orderId);

        orderItem = dbClient.executeUpdate(insertOrderItemsSql, orderItem,
                (preparedStatement, orderItem_) -> {
                    preparedStatement.setInt(1, orderItem_.getOrderId());
                    preparedStatement.setInt(2, orderItem_.getItemId());
                },
                null);

        return orderItem;
    }
}
