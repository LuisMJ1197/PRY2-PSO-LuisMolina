/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
            } else if (methodElement.getAttribute("name").equals("dynamic")) {
                this.setDynamicConfiguration(methodElement, config);
            } else if (methodElement.getAttribute("name").equals("paging")) {
                this.setPagedConfiguration(methodElement, config);
            } else if (methodElement.getAttribute("name").equals("segmentation")) {
                this.setSegmentationConfiguration(methodElement, config);
            }
        }
    }
    
    public void setFixedConfiguration(Element methodElement, Configuration config) {
        boolean activated = methodElement.getAttribute("activated").equals("true");
        int partitionSize = Integer.parseInt(((Element)methodElement.getElementsByTagName("partition-size").item(0)).getTextContent());
        config.setFixedPartitionConfiguration(activated, partitionSize);
    }
    
    public void setDynamicConfiguration(Element methodElement, Configuration config) {
        boolean activated = methodElement.getAttribute("activated").equals("true");
        config.setDynamicConfiguration(activated);
    }
    
    private void setPagedConfiguration(Element methodElement, Configuration config) {
        boolean activated = methodElement.getAttribute("activated").equals("true");
        int frameSize = Integer.parseInt(((Element)methodElement.getElementsByTagName("frame-size").item(0)).getTextContent());
        config.setPaginConfiguration(activated, frameSize);
    }
    
    private void setSegmentationConfiguration(Element methodElement, Configuration config) {
        boolean activated = methodElement.getAttribute("activated").equals("true");
        config.setSegmentationConfiguration(activated);
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

    private void setFCFSConfiguration(Element methodElement, Configuration config) {
        boolean activated = methodElement.getAttribute("activated").equals("true");
        config.setFcfsConfiguration(activated);
    }

    private void setRoundRobinConfiguration(Element methodElement, Configuration config) {
        boolean activated = methodElement.getAttribute("activated").equals("true");
        int quantum = Integer.parseInt(((Element)methodElement.getElementsByTagName("quantum").item(0)).getTextContent());
        config.setRoundRobinConfiguration(activated, quantum);
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

    public void saveConfig(Configuration config) {
        NodeList nodeList = this.rootElement.getElementsByTagName("memory-size");
        Element memorySizeElement = (Element) nodeList.item(0);
        // memory size config
        ((Element) memorySizeElement.getElementsByTagName("main-memory").item(0))
                .setTextContent(Integer.toString(config.getMainMemorySize()));
        ((Element) memorySizeElement.getElementsByTagName("disk-memory").item(0))
                .setTextContent(Integer.toString(config.getDiskMemorySize()));
        ((Element) this.rootElement.getElementsByTagName("os-saved-memory").item(0))
                .setTextContent(Integer.toString(config.getOsSavedMemory()));
        // Memory management config
        Element memoryManagementElement = (Element) this.rootElement.getElementsByTagName("memory-management").item(0);
        this.saveMemoryManagementConfiguration(config, memoryManagementElement);
        
        // Process management config
        Element processManagementElement = (Element) this.rootElement.getElementsByTagName("process-scheduler").item(0);
        this.saveProcessManagementConfiguration(config, processManagementElement);
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(this.doc);
            FileWriter writer = new FileWriter(new File(FILE_NAME));
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ConfigurationReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | TransformerException ex) {
            Logger.getLogger(ConfigurationReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveMemoryManagementConfiguration(Configuration config, Element memoryManagementElement) {
        NodeList methodList = memoryManagementElement.getElementsByTagName("method");
        for (int i = 0; i < methodList.getLength(); i++) {
            Element methodElement = (Element) methodList.item(i);
            if (methodElement.getAttribute("name").equals("fixed")) {
                this.saveFixedConfiguration(methodElement, config);
            } else if (methodElement.getAttribute("name").equals("dynamic")) {
                this.saveDynamicConfiguration(methodElement, config);
            } else if (methodElement.getAttribute("name").equals("paging")) {
                this.savePagedConfiguration(methodElement, config);
            } else if (methodElement.getAttribute("name").equals("segmentation")) {
                this.saveSegmentationConfiguration(methodElement, config);
            }
        }
    }

    private void saveFixedConfiguration(Element methodElement, Configuration config) {
        methodElement.setAttribute("activated", Boolean.toString(config.getFixedPartitionConfiguration().isActivated()));
        ((Element)methodElement.getElementsByTagName("partition-size").item(0))
                .setTextContent(Integer.toString(config.getFixedPartitionConfiguration().getPartitionSize()));
    }

    private void saveDynamicConfiguration(Element methodElement, Configuration config) {
        methodElement.setAttribute("activated", Boolean.toString(config.getDynamicPartitionConfiguration().isActivated()));
    }

    private void savePagedConfiguration(Element methodElement, Configuration config) {
        methodElement.setAttribute("activated", Boolean.toString(config.getPagingConfiguration().isActivated()));
        ((Element)methodElement.getElementsByTagName("frame-size").item(0))
                .setTextContent(Integer.toString(config.getPagingConfiguration().getFrameSize()));
    }
    
    private void saveSegmentationConfiguration(Element methodElement, Configuration config) {
        methodElement.setAttribute("activated", Boolean.toString(config.getPagingConfiguration().isActivated()));
    }

    private void saveProcessManagementConfiguration(Configuration config, Element processManagementElement) {
        NodeList methodList = processManagementElement.getElementsByTagName("method");
        for (int i = 0; i < methodList.getLength(); i++) {
            Element methodElement = (Element) methodList.item(i);
            if (methodElement.getAttribute("name").equals("fcfs")) {
                this.saveFCFSConfiguration(methodElement, config);
            } else if (methodElement.getAttribute("name").equals("rr")) {
                this.saveRoundRobinConfiguration(methodElement, config);
            } else if (methodElement.getAttribute("name").equals("srt")) {
                this.saveSRTConfiguration(methodElement, config);
            } else if (methodElement.getAttribute("name").equals("sjf")) {
                this.saveSJFConfiguration(methodElement, config);
            } else if (methodElement.getAttribute("name").equals("hrrn")) {
                this.saveHRRNConfiguration(methodElement, config);
            }    
        }
    }

    private void saveFCFSConfiguration(Element methodElement, Configuration config) {
        methodElement.setAttribute("activated", Boolean.toString(config.getFcfsConfiguration().isActivated()));
    }

    private void saveRoundRobinConfiguration(Element methodElement, Configuration config) {
        methodElement.setAttribute("activated", Boolean.toString(config.getRoundRobinConfiguration().isActivated()));
        ((Element)methodElement.getElementsByTagName("quantum").item(0))
                .setTextContent(Integer.toString(config.getRoundRobinConfiguration().getCycleClockAmount()));
    }

    private void saveSRTConfiguration(Element methodElement, Configuration config) {
        methodElement.setAttribute("activated", Boolean.toString(config.getSrtConfiguration().isActivated()));
    }

    private void saveSJFConfiguration(Element methodElement, Configuration config) {
        methodElement.setAttribute("activated", Boolean.toString(config.getSjfConfiguration().isActivated()));
    }

    private void saveHRRNConfiguration(Element methodElement, Configuration config) {
        methodElement.setAttribute("activated", Boolean.toString(config.getHrrnConfiguration().isActivated()));
    }
    
}
