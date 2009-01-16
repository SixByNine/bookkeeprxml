/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bookkeepr.xmlable;

import bookkeepr.xml.StringConvertable;
import bookkeepr.xml.XMLAble;
import coordlib.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kei041
 */
public class ClassifiedCandidateSelectRequest implements XMLAble {

    private long exactId;
    private String gridId = null;
    private Coordinate target = null;
    private float targetSeperation = 10;
    private String obsStatus=null;
    private String confStatus=null;
    private int candClassInt=-1;

    public long getExactId() {
        return exactId;
    }

    public void setExactId(long exactId) {
        this.exactId = exactId;
    }

    public String getGridId() {
        return gridId;
    }

    public void setGridId(String gridId) {
        this.gridId = gridId;
    }

    public Coordinate getTarget() {
        return target;
    }

    public void setTarget(Coordinate target) {
        this.target = target;
    }

    public float getTargetSeperation() {
        return targetSeperation;
    }

    public void setTargetSeperation(float targetSeperation) {
        this.targetSeperation = targetSeperation;
    }

    public String getObsStatus() {
        return obsStatus;
    }

    public void setObsStatus(String obsStatus) {
        this.obsStatus = obsStatus;
    }

    public String getConfStatus() {
        return confStatus;
    }

    public void setConfStatus(String confStatus) {
        this.confStatus = confStatus;
    }

    public int getCandClassInt() {
        return candClassInt;
    }

    public void setCandClassInt(int candClassInt) {
        this.candClassInt = candClassInt;
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
        xmlParameters.put("ExactId", StringConvertable.ID);
        xmlParameters.put("GridId", StringConvertable.STRING);
        xmlParameters.put("Target", StringConvertable.COORDINATE);
        xmlParameters.put("TargetSeperation", StringConvertable.FLOAT);
        xmlParameters.put("ObsStatus", StringConvertable.STRING);
        xmlParameters.put("ConfStatus", StringConvertable.STRING);
        xmlParameters.put("CandClassInt", StringConvertable.INT);
    }
}
