package com.masaya.mybatis.mybatis.parsing;

import com.masaya.mybatis.mybatis.io.MyResources;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class MyXMLEntityResolver implements EntityResolver {

    private static final String MYBATIS_CONFIG_SYSTEM = "mybatis-1-config.dtd";
    private static final String MYBATIS_MAPPER_SYSTEM = "mybatis-1-mapper.dtd";

    private static final String MYBATIS_CONFIG_DTD = "com/masaya/mybatis/mybatis/parsing/mybatis-1-config.dtd";
    private static final String MYBATIS_MAPPER_DTD = "com/masaya/mybatis/mybatis/parsing/mybatis-1-config.dtd";

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        try {
            if (systemId != null) {
                String lowerCaseSystemId = systemId.toLowerCase(Locale.ENGLISH);
                if (lowerCaseSystemId.contains(MYBATIS_CONFIG_SYSTEM)) {
                    return getInputSource(MYBATIS_CONFIG_DTD, publicId, systemId);
                } else if (lowerCaseSystemId.contains(MYBATIS_MAPPER_SYSTEM)) {
                    return getInputSource(MYBATIS_MAPPER_DTD, publicId, systemId);
                }
            }

            return null;
        } catch (Exception e) {
            throw new SAXException(e.toString());
        }
    }

    public InputSource getInputSource(String path, String publicId, String systemId) {
        InputSource source = null;
        if (path != null) {
            try {
                InputStream in = MyResources.getResourceAsStream(path);
                source = new InputSource(in);
                source.setPublicId(publicId);
                source.setSystemId(systemId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return source;
    }
}
