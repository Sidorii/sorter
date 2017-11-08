package com.netcracker.trainee.config.xml;

import com.netcracker.trainee.config.xml.entities.XmlFile;
import com.thoughtworks.xstream.XStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.NoSuchElementException;

/**
 * Class for parsing XML-files based on {@link XStream} class from
 * third-party library.
 *
 * @author Ivan Sidorenko
 * @version 1.0
 * @see XmlFile
 * @see XStream
 * @since 1.0
 */
public class XStreamXmlParser implements XmlParser {

    private XStream xStream;

    public XStreamXmlParser() {
        xStream = new XStream();
        xStream.processAnnotations(XmlFile.class);
    }

    /**
     * Parse XML-file within <b>xFile</b> path.
     *
     * @param xFile path for required XML-file to be parsed.
     * @return XmlFile instance that describe XML-file in objects model.
     * @throws FileNotFoundException    if file by current path does not found.
     * @throws NoSuchElementException   if could not find system resource based on current path.
     * @throws IllegalArgumentException if {@code String xFile} parameter if null or empty.
     * @see XmlFile
     * @see URL
     * @see ClassLoader
     * @see XStream
     */
    public XmlFile parseXml(String xFile) throws FileNotFoundException {

        if (xFile == null || xFile.equals("")) {
            throw new IllegalArgumentException("Method does not support null or empty XML-file name." +
                    " XML name is: " + xFile);
        }

        URL url = ClassLoader.getSystemResource(xFile);

        if (url == null) {
            throw new NoSuchElementException("Cannot find url for XML-file by name: " + xFile);
        }

        return (XmlFile) xStream.fromXML(new FileInputStream(url.getFile()));
    }
}
