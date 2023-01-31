package main.java.dalleHoodie.repository;

import main.java.dalleHoodie.model.Item;
import main.java.dalleHoodie.model.Order;
import main.java.dalleHoodie.model.OrderItem;
import main.java.dalleHoodie.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepository{

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

    private Connection connection = null;

    public OrdersRepository(Connection connection) {
        this.connection = connection;
    }

    public Order getOrder(int orderId) {
        Order order = new Order();
        String selectOrderSql = "select * from orders";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectOrderSql);
            if (resultSet.next()) {
                this.setOrder(order, resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

        try {
            Statement statement = connection.createStatement();
            String selectUserSql = "select user_id from users";
            ResultSet resultSet = statement.executeQuery(selectUserSql);
            if (!resultSet.next()) {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Order order = new Order();
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setUserId(userId);
        order.setCondition(ConditionList.DRAFT);
        try {
            String insertOrderSql = "insert into orders (user_id, condition, order_date, price) " +
                    "values (?, ?, ?, ?) returning order_id";
            PreparedStatement preparedStatement = connection.prepareStatement(
                    insertOrderSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setString(2, order.getCondition());
            preparedStatement.setTimestamp(3, order.getOrderDate());
            preparedStatement.setInt(4, order.getPrice());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setOrderId(resultSet.getInt("order_id"));
                return order;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> getOrders(int userId, String condition) {
        List<Order> orders = new ArrayList<Order>();
        String additionalCriteriaSql = "";
        if (condition != null)
            additionalCriteriaSql += " and condition = '" + condition + "'";
        String selectOrdersSql = "select * from orders where user_id = "
                + userId + additionalCriteriaSql;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectOrdersSql);
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("order_id"));
                order.setOrderDate(resultSet.getTimestamp("order_date"));
                order.setCondition(resultSet.getString("condition"));
                order.setPrice(resultSet.getInt("price"));
                order.setUserId(resultSet.getInt("user_id"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public List<Integer> getItemIds(int orderId) {
        List<Integer> itemIds = new ArrayList<Integer>();
        String selectItemsSql = "select item_id from order_items where order_id  = "
        + orderId;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectItemsSql);
            while (resultSet.next()) {
                itemIds.add(resultSet.getInt("item_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemIds;
    }

    public OrderItem addItem(int orderId, int itemId) {
        Constants err = Constants.NO_ORDER;
        String selectOrderSql = "select order_id from orders";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectOrderSql);
            if (resultSet.next())
                err = Constants.SUCCESS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (err != Constants.SUCCESS)
            return null;

        err = Constants.NO_ITEM;
        String selectItemSql = "select item_id from items";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectItemSql);
            if (resultSet.next())
                err = Constants.SUCCESS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (err != Constants.SUCCESS)
            return null;

        String insertOrderItemsSql = "insert into order_items (order_id, item_id) " +
                "values (" + orderId + ", " + itemId + ")";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertOrderItemsSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(itemId);
        orderItem.setOrderId(orderId);
        return orderItem;
    }
}
