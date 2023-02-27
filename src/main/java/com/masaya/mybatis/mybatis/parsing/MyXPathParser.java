package com.masaya.mybatis.mybatis.parsing;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;

public class MyXPathParser {

    private Document document;

    private XPath xpath;

    public MyXPathParser(InputStream inputStream) {
        this.xpath = getXpath();
        this.document = createDocument(new InputSource(inputStream));
    }

    public XPath getXpath() {
        XPathFactory factory = XPathFactory.newInstance();
        return factory.newXPath();
    }

    private Document createDocument(InputSource inputSource) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(false);
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(false);
            factory.setCoalescing(false);
            factory.setExpandEntityReferences(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setEntityResolver(new MyXMLEntityResolver());

            builder.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException exception) throws SAXException {

                }

                @Override
                public void error(SAXParseException exception) throws SAXException {
                    throw exception;
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    throw exception;
                }
            });

            return builder.parse(inputSource);
        } catch (Exception e) {
            throw new RuntimeException("Error creating document instance. Cause: " + e, e);
        }
    }

    public Object evaluate(String expression) {
        try {
            return xpath.evaluate(expression, document, XPathConstants.NODE);
        } catch (Exception e) {
            throw new RuntimeException("Error evaluating XPath. Cause: " + e, e);
        }
    }
}
