/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.processor.instruction;

/**
 *
 * @author Luism
 */
public interface IInstruction {
    public static final int LOAD = 2;
    public static final int STORE = 2;
    public static final int MOV = 1;
    public static final int ADD = 3;
    public static final int SUB = 3;
    public static final int INC = 2;
    public static final int DEC = 2;
    public static final int INT20 = -1;
    public static final int INT09 = -1;
    public static final int INT10 = -1;
    public static final int JUM = 2;
    public static final int CMP = 2;
    public static final int JE = 2;
    public static final int JNE = 2;
    public static final int PUSH = 1;
    public static final int POP = 1;
    public static final int PARAM = 3;
    
    public boolean execute();
    public int getHeight();
    public int incrementExecutionTime();
    public int getExecutionTime();
    public boolean isDone();
    public void movePc(int offset);
}
