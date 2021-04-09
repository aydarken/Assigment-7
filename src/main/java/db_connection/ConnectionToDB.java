package db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {
    private static final String url = "jdbc:mysql://localhost:3306/discussion forum?useUnicode=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "";

    public static Connection getNewConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}
