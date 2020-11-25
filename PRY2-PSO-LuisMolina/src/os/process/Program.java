/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.process;

/**
 *
 * @author Luism
 */
public class Program {
    private String name;
    private String[] lines;
    private String[] compiledLines;
    private String error = "";
    private boolean admitted = true;
    
    public Program(String name, String[] lines) {
        this.name = name;
        this.lines = lines;
    }
    
    public String[] getCompiledLines() {
        return compiledLines;
    }

    public void setCompiledLines(String[] compiledLines) {
        this.compiledLines = compiledLines;
    }
    
    public String getName() {
        return name;
    }

    public String[] getLines() {
        return lines;
    }

    public boolean isAdmitted() {
        return admitted;
    }

    public void setAdmitted(boolean admitted) {
        this.admitted = admitted;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}
