package com.netcracker.trainee.config.xml;

import com.netcracker.trainee.config.xml.entities.XmlFile;

import java.io.FileNotFoundException;

public interface XmlParser {

    XmlFile parseXml(String xmlFileName) throws FileNotFoundException;
}
