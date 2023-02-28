package dalleHoodie.repository;

import dalleHoodie.DBClient;
import dalleHoodie.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesRepository{

    DBClient dbClient;

    public CategoriesRepository(DBClient dbClient) {
        this.dbClient = dbClient;
    }

    public List<Category> getCategories() {
        String selectSql = "select * from categories";

        List<Category> categories = dbClient.executeSelect(selectSql, resultSet -> {
            List<Category> categories_ = new ArrayList<>();
            do {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setCategoryName(resultSet.getString("category_name"));
                categories_.add(category);
            } while (resultSet.next());
            return categories_;
        });

        return categories;
    }
}
