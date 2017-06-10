import factories.DecimalResultFactory;
import factories.ResultFactory;

/**
 * Created by Юлия on 10.06.2017.
 */
public class RunnerDecimal {
    public static void main(String[] args) {
        ResultFactory resultFactory = new DecimalResultFactory();
        RunnerLogic.execute(resultFactory,"results");
    }
}
