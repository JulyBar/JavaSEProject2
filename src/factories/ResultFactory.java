package factories;

import beans.Result;
import readers.IResultDAO;
import readers.ResultImplCsv;

import java.sql.Date;

/**
 * Created by Юлия on 10.06.2017.
 */
public class ResultFactory {

    public IResultDAO getResultDaoFromFactory(String file ) {
        return new ResultImplCsv(file, this);
    }

    public Result getResultFromFactory(String login, String test, Date date, int mark){
        return new Result(login, test, date, mark);
    }
    public Result getResultFromFactory(String login, String test, String date, String mark){
        return this.getResultFromFactory(login, test, Date.valueOf(date), Integer.parseInt(mark));
    }

    public double getMeanMark(double meanMark){
        return meanMark;
    }


}
