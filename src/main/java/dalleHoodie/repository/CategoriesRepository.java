package dalleHoodie.repository;

import dalleHoodie.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesRepository{

    Connection connection = null;

    public CategoriesRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<Category>();
        String selectSql = "select * from categories";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setCategoryName(resultSet.getString("category_name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }
}
