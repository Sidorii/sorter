package com.netcracker.trainee.config.xml;

import com.netcracker.trainee.config.xml.entities.XmlFile;
import com.thoughtworks.xstream.XStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

public class XSreamXmlParser implements XmlParser{

    private XStream xStream;


    public XSreamXmlParser() {
        xStream = new XStream();
        xStream.processAnnotations(XmlFile.class);
    }


    public XmlFile parseXml(String xFile) throws FileNotFoundException {
        URL url = ClassLoader.getSystemResource(xFile);

        if (url == null) {
            throw new FileNotFoundException("Xml file with current path does not exists.");
        }

        return (XmlFile) xStream.fromXML(new FileInputStream(url.getFile()));
    }

}
