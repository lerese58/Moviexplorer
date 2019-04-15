package app.dao;

import java.sql.*;
import java.util.concurrent.Callable;

public class AdvConnection {

    private volatile static AdvConnection instance = new AdvConnection();
    private Connection connection;

    public static AdvConnection getInstance() {
        AdvConnection localInstance = instance;
        if (localInstance == null) {
            synchronized (AdvConnection.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AdvConnection();
                }
            }
        }
        return localInstance;
    }

    private AdvConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/pavelchizhov/data/moviexp.db");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    public ResultSet resultTransaction(Callable<ResultSet> action) throws Exception {
        try {
            connection.setAutoCommit(false);
            ResultSet r = action.call();
            connection.commit();
            return r;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
            return null;
        }
        finally {
            connection.setAutoCommit(true);
        }
    }

    public void voidTransaction(Callable action) throws Exception {
        try {
            connection.setAutoCommit(false);
            action.call();
            connection.commit();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
        }
        finally {
            connection.setAutoCommit(true);
        }
    }

}
