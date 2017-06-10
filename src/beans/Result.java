package beans;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Юлия on 10.06.2017.
 */
public class Result {
    private String login;
    private String test;
    private Date date;
    private int mark;
    private final static SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public Result() {
    }

    public Result(String login, String test, String date, String mark){
        this.login = login;
        this.test = test;
        setDate(date);
        setMark(mark);
    }
    public Result(String login, String test, Date date, int mark){
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public void setDate(String date) {
        try {
            this.date = new Date(INPUT_DATE_FORMAT.parse(date).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setMark(String mark) {
        this.mark = Integer.parseInt(mark);
    }

    public String getStringDate() {
        return OUTPUT_DATE_FORMAT.format(date);
    }
    public String getStringMark(){
        return String.valueOf(mark);
    }


    @Override
    public String toString() {
        return login + ";" + test + ";" + getStringDate() + ";" + getStringMark() ;
    }
}
