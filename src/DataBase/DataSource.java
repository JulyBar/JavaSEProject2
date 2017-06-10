package DataBase;

import java.sql.*;

/**
 * Created by Юлия on 10.06.2017.
 */
public class DataSource {
    private static DataSource instance;
    private Connection con = null;
    private static final String DB_URL = "jdbc:mysql://localhost/results";
    private static final String USER = "jse";
    private static final String PASSWORD = "jse";

    private DataSource() throws SQLException{
        con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public static DataSource getInstance() throws SQLException{

        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {

        return con;
    }

    public void closeConnection() throws SQLException {
        if (con != null) {
            con.close();
            instance = null;
        }

    }

    public Statement getStatement() throws SQLException {
        return con.createStatement();
    }

    public void closeStatement(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    public PreparedStatement getPreparedStatement(String sqlInquiry) throws SQLException {
        return con.prepareStatement(sqlInquiry);
    }
    public PreparedStatement getPreparedStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return con.prepareStatement(sql, autoGeneratedKeys);
    }


    public void closePreparedStatement(PreparedStatement ps) throws SQLException {
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
    }

    public void closeResultSet(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();

        }
    }


}