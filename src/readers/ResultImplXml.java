package readers;

import beans.DecimalResult;
import beans.Result;
import handler.ResultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Юлия on 10.06.2017.
 */
public class ResultImplXml implements IResultDAO {
    final static String FILE_EXTENSION = ".xml";
    final static String FILE_PACKAGE = "src/";
    int i;
    public List<DecimalResult> results = new ArrayList<DecimalResult>();

    public ResultImplXml(String fileName) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        DefaultHandler handler = new ResultHandler();
        results = ((ResultHandler) handler).getResults();
        try {
            saxParser.parse(FILE_PACKAGE + fileName + FILE_EXTENSION, handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Result nextResult() {
        Result res = null;
        if(i<results.size()) {
            res = results.get(i);
            i++;
        }
        return res;
    }

    @Override
    public boolean hasResult() {
        return i < results.size();
    }

    @Override
    public void closeReader() {
    }
}
