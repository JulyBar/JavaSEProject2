import factories.HalfResultFactory;
import factories.ResultFactory;

/**
 * Created by Юлия on 10.06.2017.
 */
public class RunnerHalf {
    public static void main(String[] args) {
        ResultFactory resultFactory = new HalfResultFactory();
        RunnerLogic.execute(resultFactory,"results2");
    }
}
