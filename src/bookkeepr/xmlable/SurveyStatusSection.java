/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bookkeepr.xmlable;

import bookkeepr.xml.StringConvertable;
import bookkeepr.xml.XMLAble;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kei041
 */
public class SurveyStatusSection implements XMLAble {

    long configurationId;
    int totalPointings;
    int observedPointings;
    float percentComplete;
    String configName="NONE";

    public long getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(long configurationId) {
        this.configurationId = configurationId;
    }

    public int getObservedPointings() {
        return observedPointings;
    }

    public void setObservedPointings(int observedPointings) {
        this.observedPointings = observedPointings;
    }

    public float getPercentComplete() {
        return percentComplete;
    }

    public void setPercentComplete(float percentComplete) {
        this.percentComplete = percentComplete;
    }

    public int getTotalPointings() {
        return totalPointings;
    }

    public void setTotalPointings(int totalPointings) {
        this.totalPointings = totalPointings;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
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
        xmlParameters.put("ConfigurationId", StringConvertable.ID);
        xmlParameters.put("TotalPointings", StringConvertable.INT);
        xmlParameters.put("ObservedPointings", StringConvertable.INT);
        xmlParameters.put("PercentComplete", StringConvertable.FLOAT);
                xmlParameters.put("ConfigName", StringConvertable.STRING);

    }
    
}
