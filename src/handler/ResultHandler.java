package handler;

import beans.DecimalResult;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Юлия on 10.06.2017.
 */
public class ResultHandler extends DefaultHandler {
    private String login;
    private List<DecimalResult> results = new ArrayList<>();

    public ResultHandler() {

    }

    private static enum Tags {
        STUDENT, RESULTS, TESTS, LOGIN, TEST;
    }

    private Tags tag;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        tag = Tags.valueOf(qName.toUpperCase());

        if (tag == Tags.TEST) {
            final int TEST_INDEX = 0, DATE_INDEX = 1, MARK_INDEX = 2;
            results.add(new DecimalResult(login, attributes.getValue(TEST_INDEX), attributes.getValue(DATE_INDEX),
                    attributes.getValue(MARK_INDEX)));
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (tag == Tags.LOGIN) {
            String str = new String(ch, start, length).trim();
            if (!str.isEmpty()) {
                login = str;
            }

        }
    }

    public List<DecimalResult> getResults() {
        return results;
    }
}
