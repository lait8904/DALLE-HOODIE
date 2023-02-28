package dalleHoodie;

import java.sql.SQLException;

@FunctionalInterface
public interface BeCheckedProcedure <T1, T2> {
    void apply(T1 t1, T2 t2) throws SQLException;
}
