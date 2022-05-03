import lombok.SneakyThrows;
import lombok.var;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.DriverManager;


public class DBHelper {
    private static final QueryRunner runner = new QueryRunner();
    private static String dbURL = System.getProperty("url");
    private static String dbUser = System.getProperty("user");
    private static String dbPass = System.getProperty("password");


    @SneakyThrows
    public static String paymentStatus() {
        String paymentStatus;
        try (var conn = DriverManager.getConnection(dbURL, dbUser, dbPass)) {
            paymentStatus = runner(conn, "SELECT status FROM payment_entity", new ScalarHandler<>());
        }
        return paymentStatus;
    }
}
