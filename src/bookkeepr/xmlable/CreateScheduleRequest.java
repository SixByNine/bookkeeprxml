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

import bookkeepr.xml.StringConvertable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import bookkeepr.xml.XMLAble;

/**
 *
 * @author kei041
 */
public class CreateScheduleRequest implements XMLAble {

    private float startLst = 0;
    private float totalTime = 1;
    private float zenithOffset = 2;
    private boolean normalise = false;
    private float maxDec = 90;
    private float minDec = -90;
    private long configurationId;

    public float getMaxDec() {
        return maxDec;
    }

    public void setMaxDec(float maxDec) {
        this.maxDec = maxDec;
    }

    public float getMinDec() {
        return minDec;
    }

    public void setMinDec(float minDec) {
        this.minDec = minDec;
    }

    public boolean getNormalise() {
        return normalise;
    }

    public void setNormalise(boolean normalise) {
        this.normalise = normalise;
    }

    public float getStartLst() {
        return startLst;
    }

    public void setStartLst(float startLst) {
        this.startLst = startLst;
    }

    public long getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(long configurationId) {
        this.configurationId = configurationId;
    }

    
    public float getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(float totalTime) {
        this.totalTime = totalTime;
    }

    public float getZenithOffset() {
        return zenithOffset;
    }

    public void setZenithOffset(float zenithOffset) {
        this.zenithOffset = zenithOffset;
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
    protected static HashMap<String, StringConvertable> xmlParameters = new HashMap<String, StringConvertable>();
    protected static ArrayList<String> xmlSubObjects = new ArrayList<String>();
    

    static {
        xmlParameters.put("StartLst", StringConvertable.FLOAT);
        xmlParameters.put("TotalTime", StringConvertable.FLOAT);
        xmlParameters.put("ConfigurationId", StringConvertable.ID);
        xmlParameters.put("ZenithOffset", StringConvertable.FLOAT);
        xmlParameters.put("Normalise", StringConvertable.BOOLEAN);
        xmlParameters.put("MaxDec", StringConvertable.FLOAT);
        xmlParameters.put("MinDec", StringConvertable.FLOAT);
    }
}
