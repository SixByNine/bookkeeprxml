/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bookkeepr.xml;

/**
 *
 * @author kei041
 */
public class HexLookup {

    public static final String[] twochar = new String[256];
    
    static{
        for(int i = 0; i < 256; i++){
            String s = Integer.toHexString(i);
            while(s.length() < 2)s = "0"+s;
            twochar[i] = s;
        }
    }
    
    
}
