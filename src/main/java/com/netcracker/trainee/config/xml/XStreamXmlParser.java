package com.netcracker.trainee.config.xml;

import com.netcracker.trainee.config.xml.entities.XmlFile;
import com.thoughtworks.xstream.XStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.NoSuchElementException;

public class XStreamXmlParser implements XmlParser {

    private XStream xStream;


    public XStreamXmlParser() {
        xStream = new XStream();
        xStream.processAnnotations(XmlFile.class);
    }


    public XmlFile parseXml(String xFile) throws FileNotFoundException {

        if (xFile == null || xFile.equals("")) {
            throw new IllegalArgumentException("Method does not support null or empty xml file name. Xml name is: " + xFile);
        }
        URL url = ClassLoader.getSystemResource(xFile);

        if (url == null) {
            throw new NoSuchElementException("Cannot find url for xml file by name: " + xFile);
        }
        return (XmlFile) xStream.fromXML(new FileInputStream(url.getFile()));
    }
}
