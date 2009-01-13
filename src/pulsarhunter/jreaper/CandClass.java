/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pulsarhunter.jreaper;

/**
 *
 * @author kei041
 */
public enum CandClass {

    
    /*
     * 0000 UN
     * 0001 C1
     * 0010 C2
     * 0011 C3
     * 0100 Known
     * 0101 De
     * 0110 Rfi
     * 
     */
    
    
    KnownPsr(4),Class1(1), Class2(2), Class3(3),Unclassified(0),Declassified(5),Interference(6);
    
    private int intClass;
    CandClass(int intClass){
        this.intClass = intClass;
    }

    public int getIntClass() {
        return intClass;
    }
    
    public static CandClass fromIntClass(int cl){
        switch(cl){
            case 0:
                return Unclassified;
            case 1:
                return Class1;
            case 2:
                return Class2;
            case 3:
                return Class3;
            case 4:
                return KnownPsr;
            case 5:
                return Declassified;
            case 6:
                return Interference;
                    
        }
        return Unclassified;
    }
    
    
}
