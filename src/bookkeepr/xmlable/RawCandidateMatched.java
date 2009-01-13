/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bookkeepr.xmlable;

import bookkeepr.xml.StringConvertable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pulsarhunter.jreaper.HarmonicType;

/**
 *
 * @author kei041
 */
public class RawCandidateMatched extends RawCandidateBasic {

    private HarmonicType harmType;
    private boolean confirmed = false;
    private boolean prefered = false;

    public RawCandidateMatched() {
        harmType = HarmonicType.None;
    }

    
    
    public RawCandidateMatched(HarmonicType harmType, RawCandidateBasic basic) {
        this.harmType = harmType;
        this.setAccel(basic.getAccel());
        this.setBaryPeriod(basic.getBaryPeriod());
        this.setCandidateListId(basic.getCandidateListId());
        this.setDm(basic.getDm());
        this.setFoldSnr(basic.getFoldSnr());
        this.setId(basic.getId());
        this.setJerk(basic.getJerk());
        this.setMjd(basic.getMjd());
        this.setReconSnr(basic.getReconSnr());
        this.setSpecSnr(basic.getSpecSnr());
        this.setTopoPeriod(basic.getTopoPeriod());

    }

    @Override
    public Map<String, Float> getScores() {
        throw new UnsupportedOperationException("RawCandidateMatched does not store scores");
    }

    public boolean getPrefered() {
        return prefered;
    }

    public void setPrefered(boolean prefered) {
        this.prefered = prefered;
    }

    public boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getHarmType() {
        return harmType.toString();
    }

    public void setHarmType(String harmType) {
        try {
            this.harmType = HarmonicType.valueOf(harmType);
        } catch (EnumConstantNotPresentException e) {
            this.harmType = HarmonicType.None;
        }
    }

    public HarmonicType getHarmonicType() {
        return harmType;
    }

    public void setHarmType(HarmonicType harmType) {
        this.harmType = harmType;
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public HashMap<String, StringConvertable> getXmlParameters() {
        return xmlParameters;
    }

    @Override
    public List<String> getXmlSubObjects() {
        return xmlSubObjects;
    }

    @Override
    public Map<String, String> getXmlAttributes() {
        HashMap<String, String> attr = new HashMap<String, String>();
        return attr;
    }

    @Override
    public List<String> getXmlComplexElements() {
        return xmlComplexElements;
    }

    @Override
    public void setXmlAttributes(Map<String, String> attr) {
    }
    protected static HashMap<String, StringConvertable> xmlParameters = new HashMap<String, StringConvertable>();
    protected static ArrayList<String> xmlSubObjects = new ArrayList<String>();
    protected static ArrayList<String> xmlComplexElements = new ArrayList<String>();
    

    static {
        xmlParameters.put("Id", StringConvertable.ID);
        xmlParameters.put("CandidateListId", StringConvertable.ID);
        xmlParameters.put("TopoPeriod", StringConvertable.FLOAT);
        xmlParameters.put("BaryPeriod", StringConvertable.FLOAT);
        xmlParameters.put("FoldSnr", StringConvertable.FLOAT);
        xmlParameters.put("ReconSnr", StringConvertable.FLOAT);
        xmlParameters.put("SpecSnr", StringConvertable.FLOAT);
        xmlParameters.put("Dm", StringConvertable.FLOAT);
        xmlParameters.put("Accel", StringConvertable.FLOAT);
        xmlParameters.put("Jerk", StringConvertable.FLOAT);
        xmlParameters.put("Mjd", StringConvertable.DOUBLE);

        xmlParameters.put("HarmType", StringConvertable.STRING);
        xmlParameters.put("Confirmed", StringConvertable.BOOLEAN);
        xmlParameters.put("Prefered", StringConvertable.BOOLEAN);
    }
}
