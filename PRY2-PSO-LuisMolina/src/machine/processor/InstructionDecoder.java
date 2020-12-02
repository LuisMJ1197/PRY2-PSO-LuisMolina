/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.processor;

import machine.memory.Register;
import machine.processor.instruction.IInstruction;
import machine.processor.instruction.*;

/**
 *
 * @author Luism
 */
class InstructionDecoder {
    static IInstruction decode(Core core, String instruction) {
        instruction = instruction.toUpperCase();
        if (instruction.contains("LOAD")) {
            return new LOAD(core, getRegister(core, instruction.split(" ")[1]));
        } else if (instruction.contains("STORE")) {
            return new STORE(core, getRegister(core, instruction.split(" ")[1]));
        } else if (instruction.contains("MOV")) {
            String firstReg = instruction.split(",")[0].split(" ")[1];
            String secondReg = instruction.split(",")[1].trim();
            return new MOV(core, getRegister(core, firstReg), getRegister(core, secondReg));
        } else if (instruction.contains("ADD")) {
            return new ADD(core, getRegister(core, instruction.split(" ")[1]));
        } else if (instruction.contains("SUB")) {
            return new SUB(core, getRegister(core, instruction.split(" ")[1]));
        } else if (instruction.contains("INC")) {
            return new INC(core);
        } else if (instruction.contains("DEC")) {
            return new DEC(core);
        } else if (instruction.contains("INT")) {
            return new INT(core, Integer.parseInt(instruction.split(" ")[1].replace("H", "")));
        } else if (instruction.contains("JUM")) {
            return new JUM(core, Integer.parseInt(instruction.split(" ")[1]));
        } else if (instruction.contains("JE")) {
            return new JE(core, Integer.parseInt(instruction.split(" ")[1]));
        } else if (instruction.contains("JNE")) {
            return new JNE(core, Integer.parseInt(instruction.split(" ")[1]));
        } else if (instruction.contains("CMP")) {
            String firstReg = instruction.split(",")[0].split(" ")[1];
            String secondReg = instruction.split(",")[1].trim();
            return new CMP(core, getRegister(core, firstReg), getRegister(core, secondReg));
        } else if (instruction.contains("PUSH")) {
            return new PUSH(core, getRegister(core, instruction.split(" ")[1]));
        } else if (instruction.contains("POP")) {
            return new POP(core, getRegister(core, instruction.split(" ")[1]));
        } else if (instruction.contains("PARAM")) {
            if (!instruction.contains(",")) {
                return new PARAM(core, instruction.split(" ")[1]);
            } else {
                String[] str = instruction.split(",");
                if (str.length == 2) {
                    return new PARAM(core, str[0].split(" ")[1], str[1].trim());
                } else {
                    return new PARAM(core, str[0].split(" ")[1], str[1].trim(), str[2].trim());
                }
            }
        } else {
            return null;
        }
    }
    
    static Register getRegister(Core core, String reg) {
        switch (reg) {
            case "AC":
                return core.getPCB().getAcRegister();
            case "AX":
                return core.getPCB().getAxRegister();
            case "BX":
                return core.getPCB().getBxRegister();
            case "CX":
                return core.getPCB().getCxRegister();
            case "DX":
                return core.getPCB().getDxRegister();
            default: 
                return null;
        }
    }
    
}
