/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bookkeepr.xmlable;

import bookkeepr.xml.StringConvertable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kei041
 */
public class ClassifiedCandidateIndex extends Index<ClassifiedCandidate>{

    
     public List<ClassifiedCandidate> getClassifiedCandidateList() {
        return this.getIndex();
    }

    public void addClassifiedCandidate(ClassifiedCandidate obj) {
        this.addItem(obj);
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    public HashMap<String, StringConvertable> getXmlParameters() {
        return xmlParameters;
    }

    public List<String> getXmlSubObjects() {
        return xmlSubObjects;
    }
    private static HashMap<String, StringConvertable> xmlParameters = new HashMap<String, StringConvertable>();
    private static ArrayList<String> xmlSubObjects = new ArrayList<String>();

    static {
        xmlSubObjects.add("ClassifiedCandidate");

    }
    
    
}
