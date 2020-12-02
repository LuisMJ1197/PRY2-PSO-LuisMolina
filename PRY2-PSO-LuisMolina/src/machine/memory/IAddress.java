/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.memory;

/**
 *
 * @author Luism
 */
public interface IAddress {
    
    public int getOffset();
    
    public String getAddressString();
    
    public boolean equals(IAddress address);
    
    public static String pad(String value, String charP, int count, boolean leftPad) {
        String res = "";
        if (!leftPad) res += value;
        for (int i = 0; i < count - value.length(); i++) {
            res += charP;
        }
        if (leftPad) res += value;
        return res;
    }
}
