package main.java.dalleHoodie.repository;

import main.java.dalleHoodie.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepository {

    public enum Constants {
        SUCCESS,
        SAME_LOGIN
    }

    private Connection connection = null;

    public UsersRepository(Connection connection) {
        this.connection = connection;
    }

    public Constants createUser(String email,
                                String password,
                                String firstName,
                                String lastName,
                                String address) {
        String selectUserSql = "select user_id from users where email = '" + email + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectUserSql);
            if (resultSet.next())
                return Constants.SAME_LOGIN;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String insertUser = "insert into users " +
                "(first_name, last_name, password, email, address, created_on)" +
                " values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertUser);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, address);
            preparedStatement.setTimestamp(
                    6, new Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Constants.SUCCESS;
    }

    public User findByLogin(String email) {
        String selectUserSql = "select * from users where email = '"
                + email + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectUserSql);
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setCreatedOn(resultSet.getTimestamp("created_on"));
                user.setLastLogin(resultSet.getTimestamp("last_login"));
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
