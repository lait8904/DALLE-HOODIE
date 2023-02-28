package dalleHoodie.repository;

import dalleHoodie.DBClient;
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
    private DBClient dbClient;

    public ItemsRepository(DBClient dbClient) {
        this.dbClient = dbClient;
    }

    public List<Item> getItems(int categoryId) {
        String selectItemsSql = "select * from items " +
                "where category_id = " + categoryId;

        List<Item> itemsInCategory = dbClient.executeSelect(selectItemsSql, resultSet -> {
            List<Item> itemsInCategory_ = new ArrayList<>();
            do {
                Item item = new Item();
                this.setItem(item, resultSet);
                itemsInCategory_.add(item);
            } while (resultSet.next());
            return itemsInCategory_;
        });
        return itemsInCategory;
    }

    public Item getItem(int itemId) {
        String selectItemSql = "select * from items " +
                "where item_id = " + itemId;

        Item item = dbClient.executeSelect(selectItemSql, resultSet -> {
            Item item_ = new Item();
            this.setItem(item_, resultSet);
            return item_;
        });

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
