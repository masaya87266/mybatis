package com.masaya.mybatis.mybatis.parsing;

import com.masaya.mybatis.mybatis.io.MyResources;
import com.masaya.mybatis.mybatis.mapping.MyConfiguration;
import com.masaya.mybatis.mybatis.mapping.MyEnvironment;
import com.masaya.mybatis.mybatis.mapping.MyMapperStatement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MyXMLConfigBuilder {

    private MyXPathParser myXPathParser;

    public MyXMLConfigBuilder(InputStream inputStream) {
        myXPathParser = new MyXPathParser(inputStream);
    }

    public MyConfiguration parse() {
        Node dataSourceNode = (Node) myXPathParser.evaluate("/configuration/environment/dataSource");
        Map<String, String> dataSourceMap = new HashMap<>();
        Map<String, MyMapperStatement> myMapperStatementMap = new HashMap<>();

        NodeList propertiesNodeList = dataSourceNode.getChildNodes();
        for (int i = 0; i < propertiesNodeList.getLength(); i++) {
            Node propertiesNode = propertiesNodeList.item(i);
            if (propertiesNode.getNodeType() == Node.ELEMENT_NODE) {
                String name = propertiesNode.getAttributes().getNamedItem("name").getNodeValue();
                String value = propertiesNode.getAttributes().getNamedItem("value").getNodeValue();
                dataSourceMap.put(name, value);
            }
        }

        MyEnvironment myEnvironment = new MyEnvironment();
        myEnvironment.setDriver(dataSourceMap.get("driver"));
        myEnvironment.setPassword(dataSourceMap.get("password"));
        myEnvironment.setUrl(dataSourceMap.get("url"));
        myEnvironment.setUserName(dataSourceMap.get("username"));

        Node mappersNode = (Node) myXPathParser.evaluate("/configuration/mappers");
        NodeList mapperNodeList = mappersNode.getChildNodes();
        for (int i = 0; i < mapperNodeList.getLength(); i++) {
            Node mapperNode = mapperNodeList.item(i);
            if (mapperNode.getNodeType() == Node.ELEMENT_NODE) {
                String resource = mapperNode.getAttributes().getNamedItem("resource").getNodeValue();
                InputStream inputStream = MyResources.getResourceAsStream(resource);
                this.myXPathParser = new MyXPathParser(inputStream);
                Node mapperXmlNode = (Node) myXPathParser.evaluate("/mapper");
                String namespace = mapperXmlNode.getAttributes().getNamedItem("namespace").getNodeValue();
                NodeList mapperXmlNodeList = mapperXmlNode.getChildNodes();
                for (int j = 0; j < mapperXmlNodeList.getLength(); j++) {
                    Node mapperSQLNode = mapperXmlNodeList.item(j);
                    if (mapperSQLNode.getNodeType() == Node.ELEMENT_NODE) {
                        String id = mapperSQLNode.getAttributes().getNamedItem("id").getNodeValue();
                        String parameterType = mapperSQLNode.getAttributes().getNamedItem("parameterType").getNodeValue();
                        String resultType = mapperSQLNode.getAttributes().getNamedItem("resultType").getNodeValue();
                        String sql = mapperSQLNode.getTextContent();

                        MyMapperStatement myMapperStatement = new MyMapperStatement();
                        myMapperStatement.setId(id);
                        myMapperStatement.setParameterType(parameterType);
                        myMapperStatement.setResultType(resultType);
                        myMapperStatement.setSqlId(sql);
                        myMapperStatement.setNamespace(namespace);

                        myMapperStatementMap.put(namespace + "." + id, myMapperStatement);
                    }
                }
            }
        }

        MyConfiguration myConfiguration = new MyConfiguration();
        myConfiguration.setMyMapperStatementMap(myMapperStatementMap);
        myConfiguration.setMyEnvironment(myEnvironment);
        return myConfiguration;
    }
}
