import DataBase.DataSource;
import beans.Result;
import factories.ResultFactory;
import readers.IResultDAO;
import readers.ResultsLoader;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Юлия on 10.06.2017.
 */
public class RunnerLogic {


    public static void execute(ResultFactory resFactory, String fileName) {
        final String SELECT_MEAN_MARK = "SELECT  logins.name, AVG(results.mark) FROM results JOIN logins ON logins.idLogin = results.loginId GROUP BY loginId  ORDER BY mark DESC";
        final String SELECT_DATE = "SELECT logins.name, tests.name, results.dat, results.mark FROM results JOIN logins  ON logins.idLogin = results.loginId JOIN  tests ON tests.idTest = results.testID " +
                "WHERE MONTH(dat) = MONTH(NOW()) AND YEAR(dat) = YEAR(NOW()) ORDER BY dat";
        final String OUTPUT_FORMAT = "%s %1.2f \n";
        String className = "org.gjt.mm.mysql.Driver";

        try {
            Class.forName(className);
            DataSource dSourse = DataSource.getInstance();
            IResultDAO reader = resFactory.getResultDaoFromFactory(fileName);
            Statement st = null;
            ResultSet rs = null;
            try {
                ResultsLoader.loadResults(reader);
                st = dSourse.getStatement();
                rs = st.executeQuery(SELECT_MEAN_MARK);

                while (rs.next()) {
                    System.out.printf(OUTPUT_FORMAT, rs.getString(1), resFactory.getMeanMark(rs.getDouble(2)));
                }
                System.out.println();

                List<Result> results = new LinkedList<Result>();

                ResultsLoader.loadFromBaseToList(SELECT_DATE, rs, st, resFactory, results);

                ListIterator<Result> listIter = results.listIterator();
                while(listIter.hasNext()){
                    System.out.println(listIter.next());
                }
                System.out.println();
                int listSize = results.size();
                if (listSize > 0) {
                    Result result = listIter.previous();
                    Date date = result.getDate();
                    int i = listSize-1;
                    while (i>=0&result.getDate().equals(date)) {
                        System.out.println(result);
                        i--;
                        if (i>=0) {
                            result = listIter.previous();
                        }

                    }

                }

            } finally {

                if (rs != null && !rs.isClosed()) {
                    dSourse.closeResultSet(rs);
                }
                if (st != null) {
                    dSourse.closeStatement(st);
                }
                if (dSourse.getConnection() != null) {
                    dSourse.closeConnection();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error of loading the database");
            System.exit(0);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
