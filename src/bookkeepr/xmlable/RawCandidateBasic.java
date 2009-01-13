/*
 * Copyright (C) 2005-2008 Michael Keith, Australia Telescope National Facility, CSIRO
 * 
 * email: mkeith@pulsarastronomy.net
 * www  : www.pulsarastronomy.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package bookkeepr.xmlable;

import bookkeepr.xml.ComplicatedElementHandler;
import bookkeepr.xml.StringConvertable;
import bookkeepr.xml.XMLAbleComplex;
import coordlib.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kei041
 */
public class RawCandidateBasic extends RawCandidateStub implements XMLAbleComplex {

    private float topoPeriod = Float.NaN;
    private float baryPeriod = Float.NaN;
    private float foldSnr = Float.NaN;
    private float specSnr = Float.NaN;
    private float reconSnr = Float.NaN;
    private float dm = Float.NaN;
    private float accel = Float.NaN;
    private float jerk = Float.NaN;
    private double mjd = Float.NaN;
    private HashMap<String, Float> scores = new HashMap<String, Float>();
    private Coordinate candlistCoordinate;

    private transient float plotableScore=0;
    private transient int plotStatus = 0x0;

    public int getPlotStatus() {
        return plotStatus;
    }

    public void setPlotStatus(int plotStatus) {
        this.plotStatus = plotStatus;
    }

    public float getPlotableScore() {
        return plotableScore;
    }

    public void setPlotableScore(float plotableScore) {
        this.plotableScore = plotableScore;
    }
    
    
    
    public float getAccel() {
        return accel;
    }

    public void setAccel(float accel) {
        this.accel = accel;
    }

    public float getBaryPeriod() {
        return baryPeriod;
    }

    public void setBaryPeriod(float baryPeriod) {
        this.baryPeriod = baryPeriod;
    }

    public float getDm() {
        return dm;
    }

    public void setDm(float dm) {
        this.dm = dm;
    }

    public float getFoldSnr() {
        return foldSnr;
    }

    public void setFoldSnr(float foldSnr) {
        this.foldSnr = foldSnr;
    }

    public float getJerk() {
        return jerk;
    }

    public void setJerk(float jerk) {
        this.jerk = jerk;
    }

    public double getMjd() {
        return mjd;
    }

    public void setMjd(double mjd) {
        this.mjd = mjd;
    }

    public float getReconSnr() {
        return reconSnr;
    }

    public void setReconSnr(float reconSnr) {
        this.reconSnr = reconSnr;
    }

    public Map<String, Float> getScores() {
        return scores;
    }

    public void setScores(HashMap<String, Float> scores) {
        this.scores = scores;
    }

    public float getSpecSnr() {
        return specSnr;
    }

    public void setSpecSnr(float specSnr) {
        this.specSnr = specSnr;
    }

    public float getTopoPeriod() {
        return topoPeriod;
    }

    public void setTopoPeriod(float topoPeriod) {
        this.topoPeriod = topoPeriod;
    }

    public Coordinate getCandlistCoordinate() {
        return candlistCoordinate;
    }

    public void setCandlistCoordinate(Coordinate candlistCoordinate) {
        this.candlistCoordinate = candlistCoordinate;
    }
    
    

    public ComplicatedElementHandler getComplicatedElementHandler(String type) {
        if (type.equals("Score")) {
            return new ComplicatedElementHandler<RawCandidateBasic>() {

                RawCandidateBasic parent;
                String key = "";

                public void setParent(RawCandidateBasic item) {
                    this.parent = item;
                }

                public void startTag(String localname, Map attribites) {
                    if (localname.equals("score")) {
                        this.key = (String) attribites.get("key");
                    }
                }

                public void endTag(String localname, StringBuffer content) {
                    if (localname.equals("score")) {
                        float score = Float.parseFloat(content.toString());
                        parent.scores.put(key, score);
                    }
                }

                public List<String> createXMLLines() {
                    ArrayList<String> ret = new ArrayList<String>();
                    for (String key : parent.scores.keySet()) {
                        ret.add("<score key='" + key + "'>" + parent.scores.get(key) + "</score>");
                    }
                    return ret;
                }
            };
        }
        throw new UnsupportedOperationException("Not supported yet.");

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

    public Map<String, String> getXmlAttributes() {
        HashMap<String, String> attr = new HashMap<String, String>();
        return attr;
    }

    public List<String> getXmlComplexElements() {
        return xmlComplexElements;
    }

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

        xmlComplexElements.add("Score");
    }
}
