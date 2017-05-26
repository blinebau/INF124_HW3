package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnect {
	
	private static Connection connection;

    private DBConnect() {

    }

    public static Connection getInstance() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + DBConfig.getHost() + "/" + DBConfig.getDatabaseName(),
                        DBConfig.getUser(), DBConfig.getPassword());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return connection;
    }
	
}
