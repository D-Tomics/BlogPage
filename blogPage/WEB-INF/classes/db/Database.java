package db;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static PrintWriter out = new PrintWriter(System.out);

    private String usrname = "root";
    private String password = "root";
    private String url = "jdbc:mysql://localhost/usersDB";

    private String name;
    private String host = "localhost";

    private Connection connection;
    private boolean connected;
    private Statement statement;

    public static Database instance ;
    public static Database getDatabase(String name) {
        if(instance == null)
            instance = new Database();
        instance.name = name;
        instance.url = "jdbc:mysql://" + instance.host + "/" + name;
        return instance;
    }

    public static void setHost(String host) {
        instance.host = host;
    }

    public Database() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
             e.printStackTrace(out);
        }
    }

    public void connect() throws SQLException {
        if (!connected) {
            connection = DriverManager.getConnection(url, usrname, password);
            statement = connection.createStatement();        
            connected = true;
        }
    }

    public boolean execute(String sql) {
        try {
            if(!connected) connect();
            return statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace(out);
        }
        return false;
    }

    public ResultSet executeQuery(String sql) {
        try {
            if(!connected) connect();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace(out);
        }
        return null;
    }

    public int executeUpdate(String sql) {
        try {
            if(!connected) connect();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace(out);
        }
        return 0;
    } 

    public void close() {
        try {
            if(!connected) return;
            connected = false;
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace(out);
        }
    }

    public String getName() {
        return name;
    }

    public Table getTable(String name) {
        Table table = new Table();
        Table.setOut(out);
        table.setDb(this);
        table.setName(name);
        table.setColumns();
        return table;
    }
}