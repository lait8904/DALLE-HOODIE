package dalleHoodie;
import dalleHoodie.repository.OrdersRepository;

import java.sql.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class DBClient {
    private final String URL = "jdbc:postgresql://localhost/dalle_hoodie";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "postgres";

    private Connection connection = null;
    public Connection getConnection() {
        return connection;
    }

    public DBClient(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        try {
            this.connection = DriverManager
                    .getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (this.connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

    public <T> T executeSelect(String sql, Function<ResultSet, T> consumer){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                return consumer.apply(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }





    public void executeUpdate(String sql, Function<> preparedStatemnt) {



    }
}
