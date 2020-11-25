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
        
        nodeList = this.rootElement.getElementsByTagName("os-saved-memory");
        Element osSavedMemoryElement = (Element) nodeList.item(0);
        int osSavedMemory = Integer.parseInt(osSavedMemoryElement.getTextContent());
        config.setMainMemorySize(mainMemorySize);
        config.setDiskMemorySize(diskMemorySize);
        config.setOsSavedMemory(osSavedMemory);
        
        // Memory management config
        Element memoryManagementElement = (Element) this.rootElement.getElementsByTagName("memory-management").item(0);
        this.setMemoryManagementConfiguration(config, memoryManagementElement);
        
        // Process management config
        Element processManagementElement = (Element) this.rootElement.getElementsByTagName("process-scheduler").item(0);
        this.setProcessManagementConfiguration(config, processManagementElement);
        return config;
    }
    
    public void setMemoryManagementConfiguration(Configuration config, Element memoryManagementElement) {
        NodeList methodList = memoryManagementElement.getElementsByTagName("method");
        for (int i = 0; i < methodList.getLength(); i++) {
            Element methodElement = (Element) methodList.item(i);
            if (methodElement.getAttribute("name").equals("fixed")) {
                this.setFixedConfiguration(methodElement, config);
            }
        }
    }
    
    private void setProcessManagementConfiguration(Configuration config, Element processManagementElement) {
        NodeList methodList = processManagementElement.getElementsByTagName("method");
        for (int i = 0; i < methodList.getLength(); i++) {
            Element methodElement = (Element) methodList.item(i);
            if (methodElement.getAttribute("name").equals("fcfs")) {
                this.setFCFSConfiguration(methodElement, config);
            } else if (methodElement.getAttribute("name").equals("rr")) {
                this.setRoundRobinConfiguration(methodElement, config);
            } else if (methodElement.getAttribute("name").equals("srt")) {
                this.setSRTConfiguration(methodElement, config);
            } else if (methodElement.getAttribute("name").equals("sjf")) {
                this.setSJFConfiguration(methodElement, config);
            } else if (methodElement.getAttribute("name").equals("hrrn")) {
                this.setHRRNConfiguration(methodElement, config);
            }    
        }
    }
    
    public void setFixedConfiguration(Element methodElement, Configuration config) {
        boolean activated = methodElement.getAttribute("activated").equals("true");
        int partitionSize = Integer.parseInt(((Element)methodElement.getElementsByTagName("partition-size").item(0)).getTextContent());
        config.setFixedPartitionConfiguration(activated, partitionSize);
    }

    private void setFCFSConfiguration(Element methodElement, Configuration config) {
        boolean activated = methodElement.getAttribute("activated").equals("true");
        config.setFcfsConfiguration(activated);
    }

    private void setRoundRobinConfiguration(Element methodElement, Configuration config) {
        boolean activated = methodElement.getAttribute("activated").equals("true");
        int cycleClock = Integer.parseInt(((Element)methodElement.getElementsByTagName("clock").item(0)).getTextContent());
        config.setRoundRobinConfiguration(activated, cycleClock);
    }
    
    private void setSRTConfiguration(Element methodElement, Configuration config) {
        boolean activated = methodElement.getAttribute("activated").equals("true");
        config.setSRTConfiguration(activated);
    }
    
    private void setSJFConfiguration(Element methodElement, Configuration config) {
        boolean activated = methodElement.getAttribute("activated").equals("true");
        config.setSJFConfiguration(activated);
    }
    
    private void setHRRNConfiguration(Element methodElement, Configuration config) {
        boolean activated = methodElement.getAttribute("activated").equals("true");
        config.setHRRNConfiguration(activated);
    }
    
    
}
