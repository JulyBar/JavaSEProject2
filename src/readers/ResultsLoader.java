package readers;

import DataBase.DataSource;
import beans.Result;
import factories.ResultFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Юлия on 10.06.2017.
 */
public class ResultsLoader {
    final static String DELETE_RESULTS = "DELETE FROM results";
    final static String DELETE_TESTS = "DELETE FROM tests";
    final static String DELETE_LOGINS = "DELETE FROM logins";
    final static String INSERT_RESULT = "INSERT INTO results (loginId, testId, dat, mark) VALUES (?,?,?,? )  ON DUPLICATE KEY UPDATE mark=VALUES(mark);";
    final static String INSERT_TEST = "INSERT INTO tests (name) value (?)";
    final static String INSERT_LOGIN = "INSERT INTO logins (name) values (?)";
    final static String SEARCH_LOGIN = "SELECT idLogin FROM logins WHERE name = ?";
    final static String SEARCH_TEST = "SELECT idTest FROM tests WHERE name = ?";


    public static void loadResults(IResultDAO reader) {

        try {
            DataSource dSource = DataSource.getInstance();
            clearDataBase();
            PreparedStatement psInsertResult = dSource.getPreparedStatement(INSERT_RESULT);
            PreparedStatement psInsertLogin = dSource.getPreparedStatement(INSERT_LOGIN);
            PreparedStatement psInsertTest = dSource.getPreparedStatement(INSERT_TEST);
            PreparedStatement psSearchLogin = dSource.getPreparedStatement(SEARCH_LOGIN);
            PreparedStatement psSearchTest = dSource.getPreparedStatement(SEARCH_TEST);

            try {
                while (reader.hasResult()) {
                    final int ID_LOGIN = 1;
                    final int ID_TEST = 2;
                    final int ID_DATE = 3;
                    final int ID_MARK = 4;
                    Result result = reader.nextResult();
                    String login = result.getLogin();
                    String test = result.getTest();
                    int idLogin = searchId(psSearchLogin, psInsertLogin, login);
                    int idTest = searchId(psSearchTest, psInsertTest, test);
                    psInsertResult.setInt(ID_LOGIN, idLogin);
                    psInsertResult.setInt(ID_TEST, idTest);
                    psInsertResult.setDate(ID_DATE, result.getDate());
                    psInsertResult.setInt(ID_MARK, result.getMark());
                    psInsertResult.execute();
                }
            } finally {
                dSource.closePreparedStatement(psSearchTest);
                dSource.closePreparedStatement(psSearchLogin);
                dSource.closePreparedStatement(psInsertLogin);
                dSource.closePreparedStatement(psInsertResult);
                dSource.closePreparedStatement(psInsertTest);
            }
        } catch (SQLException e) {
            System.out.println("Error of loading the database");
            System.exit(0);
        } finally {
            reader.closeReader();
        }

    }

    private static void clearDataBase() throws SQLException {
        Statement statement = DataSource.getInstance().getStatement();
        statement.execute(DELETE_RESULTS);
        statement.execute(DELETE_TESTS);
        statement.execute(DELETE_LOGINS);

    }

    private static int searchId(PreparedStatement psSearch, PreparedStatement psIn, String nameElement) throws SQLException {
        int id = 0;
        psSearch.setString(1, nameElement);
        ResultSet rs = psSearch.executeQuery();
        if (rs.next()) {
            id = rs.getInt(1);
        } else {
            psIn.setString(1, nameElement);
            psIn.executeUpdate();
            psSearch.setString(1, nameElement);
            rs = psSearch.executeQuery();
            rs.next();
            id = rs.getInt(1);
        }
        return id;
    }

    public static void loadFromBaseToList (String SQLInquiry, ResultSet rs, Statement st, ResultFactory resultFactory, List<Result> results) throws SQLException{
        final int LOGIN_IND = 1;
        final int TEST_IND = 2;
        final int DATE_IND = 3;
        final int MARK_IND = 4;

        rs = st.executeQuery(SQLInquiry);

        while (rs.next()) {

            Result result = resultFactory.getResultFromFactory(rs.getString(LOGIN_IND), rs.getString(TEST_IND), rs.getDate(DATE_IND),rs.getInt(MARK_IND));
            results.add(result);
        }

    }



}
