package com.masaya.mybatis.mybatis.parsing;

import com.masaya.mybatis.mybatis.mapping.MyConfiguration;
import org.w3c.dom.Node;

import java.io.InputStream;

public class MyXMLConfigBuilder {

    private MyXPathParser myXPathParser;

    public MyXMLConfigBuilder(InputStream inputStream) {
        myXPathParser = new MyXPathParser(inputStream);
    }

    public MyConfiguration parse() {
        Node node = (Node) myXPathParser.evaluate("/configuration");
        return null;
    }
}
