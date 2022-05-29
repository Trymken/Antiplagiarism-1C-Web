package algorithms;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class XmlParser {

    public static String getUuidFromXml(String path) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SaxHandler handler = new SaxHandler();
        File f = new File(path);
        parser.parse(f, handler);
        return handler.uuid;
    }

}


class SaxHandler extends DefaultHandler {
    String uuid = null;

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        String s = attrs.getValue("uuid");
        if (s != null && uuid == null){
            uuid = s;
        }
    }
}
