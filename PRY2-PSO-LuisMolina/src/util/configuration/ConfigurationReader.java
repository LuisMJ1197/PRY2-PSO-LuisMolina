/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Luism
 */
public class ConfigurationReader {
    private static ConfigurationReader instance;
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document doc;
    private Element rootElement;
    private static final String FILE_NAME = "config.xml";
    
    public static ConfigurationReader getInstance() throws ParserConfigurationException, SAXException, IOException {
        if (instance == null) instance = new ConfigurationReader();
        return instance;
    }
    
    private ConfigurationReader() throws ParserConfigurationException, SAXException, IOException {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        this.init();
    }
    
    private void init() throws SAXException, IOException {
        File inputFile = new File(FILE_NAME);
        this.doc = builder.parse(inputFile);
        this.doc.getDocumentElement().normalize();
        this.rootElement = this.doc.getDocumentElement();
    }
    
    public Configuration getConfiguration() {
        Configuration config = new Configuration();
        NodeList nodeList = this.rootElement.getElementsByTagName("memory-size");
        Element memorySizeElement = (Element) nodeList.item(0);
        // memory size config
        int mainMemorySize = Integer.parseInt(((Element) memorySizeElement.getElementsByTagName("main-memory").item(0)).getTextContent());
        int diskMemorySize = Integer.parseInt(((Element) memorySizeElement.getElementsByTagName("disk-memory").item(0)).getTextContent());
        config.setMainMemorySize(mainMemorySize);
        config.setDiskMemorySize(diskMemorySize);
        // fixed config
        Element memoryManagementElement = (Element) this.rootElement.getElementsByTagName("memory-management").item(0);
        NodeList methodList = memoryManagementElement.getElementsByTagName("method");
        for (int i = 0; i < methodList.getLength(); i++) {
            Element methodElement = (Element) methodList.item(i);
            if (methodElement.getAttribute("name").equals("fixed")) {
                this.setFixedConfiguration(methodElement, config);
            }
        }
        return config;
    }
    
    public void setFixedConfiguration(Element methodElement, Configuration config) {
        boolean activated = methodElement.getAttribute("activated").equals("true");
        int partitionSize = Integer.parseInt(((Element)methodElement.getElementsByTagName("partition-size").item(0)).getTextContent());
        config.setFixedPartitionConfiguration(activated, partitionSize);
    }
}
