import factories.ResultFactory;
import readers.IResultDAO;
import readers.ResultImplCsv;
import readers.ResultsLoader;

/**
 * Created by Юлия on 10.06.2017.
 */
public class RunnerInt {
    public static void main(String[] args) {
        ResultFactory resultFactory = new ResultFactory();
        RunnerLogic.execute(resultFactory,"results");
    }
}
