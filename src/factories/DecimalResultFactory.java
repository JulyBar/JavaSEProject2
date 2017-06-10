package factories;

import beans.DecimalResult;
import beans.Result;
import readers.IResultDAO;
import readers.ResultImplXml;

import java.sql.Date;

/**
 * Created by Юлия on 10.06.2017.
 */
public class DecimalResultFactory extends ResultFactory {

    public IResultDAO getResultDaoFromFactory(String file ) {
        return new ResultImplXml(file);
    }
    public Result getResultFromFactory(String login, String test, Date date, int mark){
        return new DecimalResult(login, test, date, mark);
    }
    public Result getResultFromFactory(String login, String test, String date, String mark){
        return this.getResultFromFactory(login, test, Date.valueOf(date),(int)(Double.parseDouble(mark)*10));
    }

    public double getMeanMark(double meanMark) {
        return meanMark/10;
    }
}
