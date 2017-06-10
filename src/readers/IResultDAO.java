package readers;

import beans.Result;

/**
 * Created by Юлия on 10.06.2017.
 */
public interface IResultDAO {
    Result nextResult();
    boolean hasResult();
    void closeReader();
}
