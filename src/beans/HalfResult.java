package beans;

import java.sql.Date;

/**
 * Created by Юлия on 10.06.2017.
 */
public class HalfResult extends Result{

    public HalfResult(String login, String test, String date, String mark){
        super(login, test, date, mark);

    }

    public HalfResult(String login, String test, Date date, int mark){
        super(login, test, date, mark);

    }
    public void setMark(String mark) {
        setMark((int)(Double.parseDouble(mark)*2));
    }

    public String getStringMark() {
        String strMark;
        if (getMark()%2==0){
            strMark = getMark()/2+"." + 0;
        }else strMark = getMark()/2+"." + 5;
        return strMark;
    }

}
