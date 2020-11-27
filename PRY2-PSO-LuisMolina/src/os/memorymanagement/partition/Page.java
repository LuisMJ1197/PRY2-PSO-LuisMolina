/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.memorymanagement.partition;

/**
 *
 * @author Luism
 */
public class Page {
    private String[] code;
    private int pageNumber; 
    private Frame frame;
    
    public Page(int pageNumber, String[] code) {
        this.pageNumber = pageNumber;
        this.code = code;
    }
    
    public String[] getCode() {
        return this.code;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }
    
    
}
