/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.process;

import os.OS;
import os.memorymanagement.PagingMM;
import os.memorymanagement.partition.Page;

/**
 *
 * @author Luism
 */
public class PagedProcess extends Process {
    private Page[] pageTable;
    
    public PagedProcess(String name, String[] code) {
        super(name, code);
        this.divideProcessInPages();
    }

    private void divideProcessInPages() {
        int pageSize = ((PagingMM) OS.getInstance().getMemoryManager()).getFrameSize();
        int pageCount = (int) Math.ceil(code.length / (double) pageSize);
        pageTable = new Page[pageCount];
        for (int i = 0; i < pageCount; i++) {
            String[] codeM = new String[pageSize];
            for (int j = 0; j < pageSize && i * pageSize + j < this.code.length; j++) {
                codeM[j] = this.code[i * pageSize + j];
            }
            pageTable[i] = new Page(i, codeM);
        }
    }
    
    public int getPageCount() {
        return this.pageTable.length;
    }
    
    public Page[] getPages() {
        return this.pageTable;
    }
    
}
