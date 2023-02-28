package dalleHoodie;

import java.sql.SQLException;

@FunctionalInterface
public interface BeCheckedFunction2 <T1, T2, R> {
    R apply(T1 t1, T2 t2) throws SQLException;
}
