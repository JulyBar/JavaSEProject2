package factories;

import beans.HalfResult;
import beans.Result;
import readers.IResultDAO;
import readers.ResultImplCsv;

import java.sql.Date;

/**
 * Created by Юлия on 10.06.2017.
 */
public class HalfResultFactory extends ResultFactory{
    public IResultDAO getResultDaoFromFactory(String file ) {
        return new ResultImplCsv(file, this);
    }
    public Result getResultFromFactory(String login, String test, Date date, int mark){
        return new HalfResult(login, test, date, mark);
    }
    public Result getResultFromFactory(String login, String test, String date, String mark){
        return this.getResultFromFactory(login, test,Date.valueOf(date),(int) (Double.parseDouble(mark)*2));
    }

    public double getMeanMark(double meanMark) {
        return meanMark/2;
    }
}
