package readers;

import beans.Result;
import factories.ResultFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Юлия on 10.06.2017.
 */
public class ResultImplCsv implements IResultDAO{

    private Scanner sc;
    private ResultFactory resFactory;
    final static String FILE_EXTENSION = ".csv";
    final static String FILE_PACKAGE = "src/";

    public ResultImplCsv (String fileName, ResultFactory resFactory) {
        try {
            sc = new Scanner(new FileReader(FILE_PACKAGE + fileName + FILE_EXTENSION));
            sc.useLocale(Locale.ENGLISH);
            this.resFactory = resFactory;
        } catch (FileNotFoundException e)   {
            System.err.println("Input file is not found");
            closeReader();
        }
    }

    @Override
    public Result nextResult() {
        final String DELIMETER = ";";
        final int LOGIN_INDEX = 0;
        final int TEST_INDEX = 1;
        final int DATE_INDEX = 2;
        final int MARK_INDEX = 3;
        String line;
        String[] parts;
        Result result = null;
        line = sc.nextLine();
        parts = line.split(DELIMETER);
        result = resFactory.getResultFromFactory(parts[LOGIN_INDEX], parts[TEST_INDEX], parts[DATE_INDEX], parts[MARK_INDEX]);

        return result;
    }

    @Override
    public boolean hasResult() {
        return sc.hasNextLine();
    }

    @Override
    public void closeReader() {
        if (sc != null) {
            sc.close();
        }
    }
}
