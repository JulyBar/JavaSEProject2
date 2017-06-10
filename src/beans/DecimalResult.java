package beans;

import java.sql.Date;

/**
 * Created by Юлия on 10.06.2017.
 */
public class DecimalResult extends Result{

    public DecimalResult(String login, String test, String date, String mark){
        super(login, test, date, mark);

    }
    public DecimalResult(String login, String test, Date date, int mark){
        super(login, test, date, mark);

    }
    public void setMark(String mark) {
        setMark((int)(Double.parseDouble(mark)*10));
    }

    public String getStringMark() {
        return getMark()/10 + "." + getMark()%10;
    }
}
