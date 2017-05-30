package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnect {
	
	private static Connection connection;

    private DBConnect() {

    }

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            	System.out.println(DBConfig.getHost() + "\n" + DBConfig.getDatabaseName() + "\n" + DBConfig.getUser() + "\n"
            			+ DBConfig.getPassword());
                connection = DriverManager.getConnection("jdbc:mysql://" + DBConfig.getHost() + "/" + DBConfig.getDatabaseName(),
                        DBConfig.getUser(), DBConfig.getPassword());
        }

        return connection;
    }
	
}
