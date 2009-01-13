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
import bookkeepr.xml.XMLAble;
import coordlib.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kei041
 */
public class PointingSelectRequest implements XMLAble {

    long exactId = 0;
    String gridId = null;
    Coordinate target = null;
    long configurationId = 0;
    float visibleAt = Float.NaN;
    float setAt = Float.NaN;
    float targetSeperation = 0.5f;
    boolean selectToObserve = true;
    boolean selectNotToObserve = true;
    boolean useRegularExpresions = false;
    int maxResults = 100;
    float maxTobs=Float.NaN;
    float minTobs=Float.NaN;
    long[] ignoreIdList = null;

    public long getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(long configurationId) {
        this.configurationId = configurationId;
    }

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

    public float getSetAt() {
        return setAt;
    }

    public void setSetAt(float setAt) {
        this.setAt = setAt;
    }

    public Coordinate getTarget() {
        return target;
    }

    public void setTarget(Coordinate target) {
        this.target = target;
    }

    public float getVisibleAt() {
        return visibleAt;
    }

    public void setVisibleAt(float visibleAt) {
        this.visibleAt = visibleAt;
    }

    public float getTargetSeperation() {
        return targetSeperation;
    }

    public void setTargetSeperation(float targetSeperation) {
        this.targetSeperation = targetSeperation;
    }

    public boolean getSelectNotToObserve() {
        return selectNotToObserve;
    }

    public void setSelectNotToObserve(boolean selectNotToObserve) {
        this.selectNotToObserve = selectNotToObserve;
    }

    public boolean getSelectToObserve() {
        return selectToObserve;
    }

    public void setSelectToObserve(boolean selectToObserve) {
        this.selectToObserve = selectToObserve;
    }

    public boolean getUseRegularExpresions() {
        return useRegularExpresions;
    }

    public void setUseRegularExpresions(boolean useRegularExpresions) {
        this.useRegularExpresions = useRegularExpresions;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public float getMaxTobs() {
        return maxTobs;
    }

    public float getMinTobs() {
        return minTobs;
    }

    public void setMaxTobs(float maxTobs) {
        this.maxTobs = maxTobs;
    }

    public void setMinTobs(float minTobs) {
        this.minTobs = minTobs;
    }

    public long[] getIgnoreIdList() {
        return ignoreIdList;
    }

    public void setIgnoreIdList(long[] ignoreIdList) {
        this.ignoreIdList = ignoreIdList;
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
        xmlParameters.put("ConfigurationId", StringConvertable.ID);
        xmlParameters.put("VisibleAt", StringConvertable.FLOAT);
        xmlParameters.put("SetAt", StringConvertable.FLOAT);
        xmlParameters.put("TargetSeperation", StringConvertable.FLOAT);
        xmlParameters.put("SelectToObserve", StringConvertable.BOOLEAN);
        xmlParameters.put("SelectNotToObserve", StringConvertable.BOOLEAN);
        xmlParameters.put("UseRegularExpresions", StringConvertable.BOOLEAN);
        xmlParameters.put("MaxResults", StringConvertable.INT);
        xmlParameters.put("MaxTobs", StringConvertable.FLOAT);
        xmlParameters.put("MinTobs", StringConvertable.FLOAT);
        xmlParameters.put("IgnoreIdList", StringConvertable.IDARRAY);
    }
}
