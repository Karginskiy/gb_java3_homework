package lesson2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Connector {

    private Connection connection;
    private String dbPath;

    Connector(String dbPath) {
        this.dbPath = dbPath;
    }

    void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(dbPath);
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Connection getConnection() {
        return this.connection;
    }

}
