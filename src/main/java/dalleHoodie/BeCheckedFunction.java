package dalleHoodie;

import java.sql.SQLException;

@FunctionalInterface
public interface BeCheckedFunction<T, R> {
    R apply(T t) throws SQLException;
}
