/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author Luism
 */
public class MCompiler {
    public static String[] regex = {
       "(MOV|mov)\\s[A-Da-d](X|x),(\\s)?[A-Da-d](X|x)",
       "(STORE|store)\\s[A-Da-d](X|x)",
       "(LOAD|load)\\s[A-Da-d](X|x)",
       "(ADD|add)\\s[A-Da-d](X|x)",
       "(SUB|sub)\\s[A-Da-d](X|x)",
       "(INC|inc)",
       "(DEC|dec)",
       "(INT|int)\\s20(h|H)",
       "(INT|int)\\s09(h|H)",
       "JUM(P)?\\s(-)?(0|-?[1-9][0-9]*)",
       "(CMP|cmp)\\s[A-Da-d](X|x),(\\s)?[A-Da-d](X|x)",
       "(JNE|jne)\\s(\\+|-)(0|-?[1-9][0-9]*)",
       "(JE|je)\\s(-)?(0|-?[1-9][0-9]*)",
       "(PUSH|push)\\s[A-Da-d](X|x)",
       "(POP|pop)\\s[A-Da-d](X|x)",
       "(PARAM|param)\\s(0|-?[1-9][0-9]*)(,(\\s)?(0|-?[1-9][0-9]*))?(,(\\s)?(0|-?[1-9][0-9]*))?",
    };
    
    public MCompiler() {
    
    }
    
    public void compileProgram(Program program) {
        ArrayList<String> res = new ArrayList<>(); //[program.getLines().length];
        int i = 0;
        for (String line: program.getLines()) {
            if (this.validateLine(line)) {
                res.add(this.decodeLine(line));
            } else {
                program.setError(String.format("Invalid format exception at program: %s - line: %d - %s.", program.getName(), i, line));
                program.setAdmitted(false);
            }
            i++;
        }
        program.setCompiledLines(res.toArray(new String[res.size()]));
    }
    
    private String decodeLine(String line) {
        return line;
    }

    private boolean validateLine(String line) {
        for (String rege: MCompiler.regex) {
            if (Pattern.matches(rege, line)) {
                return true;
            }
        }
        return false;
    }
}
