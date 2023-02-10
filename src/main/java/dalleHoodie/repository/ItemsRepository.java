package dalleHoodie.repository;

import dalleHoodie.model.Item;
import dalleHoodie.model.Category;
import dalleHoodie.model.Order;
import dalleHoodie.model.OrderItem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemsRepository{
    private Connection connection = null;

    public ItemsRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Item> getItems(int categoryId) {
        List<Item> itemsInCategory = new ArrayList<Item>();
        String selectItemsSql = "select * from items " +
                "where category_id = " + categoryId;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectItemsSql);
            while(resultSet.next()) {
                Item item = new Item();
                this.setItem(item, resultSet);
                itemsInCategory.add(item);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemsInCategory;
    }

    public Item getItem(int itemId) {
        Item item = new Item();
        String selectItemSql = "select * from items " +
                "where item_id = " + itemId;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectItemSql);
            if (resultSet.next()) {
                this.setItem(item, resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    private void setItem(Item item, ResultSet resultSet) throws SQLException {
        item.setItemId(resultSet.getInt("item_id"));
        item.setCategoryId(resultSet.getInt("category_id"));
        item.setSizeId(resultSet.getInt("size_id"));
        item.setPictureId(resultSet.getInt("picture_id"));
        item.setColorId(resultSet.getInt("color_id"));
        item.setItemName(resultSet.getString("item_name"));
        item.setPrice(resultSet.getInt("price"));
    }

}
