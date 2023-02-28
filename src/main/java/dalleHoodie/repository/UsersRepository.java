package dalleHoodie.repository;

import dalleHoodie.DBClient;
import dalleHoodie.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UsersRepository {

    private DBClient dbClient;

    public UsersRepository(DBClient dbClient) {
        this.dbClient = dbClient;
    }

    public User createUser(  String email,
                                String password,
                                String firstName,
                                String lastName,
                                String address) {
        String selectUserSql = "select user_id from users where email = '" + email + "'";

        Boolean exist = dbClient.executeSelect(selectUserSql, resultSet -> true);
        //TODO create returning documentation

        if (exist != null) {
            return null;
        }

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setEmail(email);
        user.setAddress(address);
        user.setLastLogin(new Timestamp(System.currentTimeMillis()));

        String insertUserSql = "insert into users " +
                "(first_name, last_name, password, email, address, created_on)" +
                " values (?, ?, ?, ?, ?, ?)";

        user = dbClient.executeUpdate(insertUserSql, user,
                (preparedStatement, user_) -> {
                    preparedStatement.setString(1, user_.getFirstName());
                    preparedStatement.setString(2, user_.getLastName());
                    preparedStatement.setString(3, user_.getPassword());
                    preparedStatement.setString(4, user_.getEmail());
                    preparedStatement.setString(5, user_.getAddress());
                    preparedStatement.setTimestamp(6, user_.getLastLogin());
                },
                (resultSet, user_) -> {
                    user_.setUserId(resultSet.getInt("user_id"));
                    return user_;
                });
        return user;
        }

    /**
     * @param email of User
     * @return User or null if it not exists
     */
    public User findByLogin(String email) {
        String selectUserSql = "select * from users where email = '"
                + email + "'";
        User user = dbClient.executeSelect(selectUserSql, resultSet -> {
            User user_ = new User();
            user_.setUserId(resultSet.getInt("user_id"));
            user_.setFirstName(resultSet.getString("first_name"));
            user_.setLastName(resultSet.getString("last_name"));
            user_.setPassword(resultSet.getString("password"));
            user_.setEmail(resultSet.getString("email"));
            user_.setAddress(resultSet.getString("address"));
            user_.setCreatedOn(resultSet.getTimestamp("created_on"));
            user_.setLastLogin(resultSet.getTimestamp("last_login"));
            return user_;
        });
        return user;
    }
}
