/*
 * Copyright (C) 2005-2007 Michael Keith, University Of Manchester
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
import bookkeepr.xml.IdAble;
import bookkeepr.xml.StringConvertable;

import bookkeepr.xml.XMLAbleComplex;
import coordlib.Coordinate;

import coordlib.SkyLocated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xml.sax.SAXException;

/**
 *
 * @author kei041
 */
public class Pointing implements XMLAbleComplex, IdAble, SkyLocated, Cloneable {

    private static Coordinate DEFAULT_COORDINATE = new Coordinate(0, 0);
    private long id = 0;
    private String gridId;
    private Coordinate target = DEFAULT_COORDINATE;
    private long configurationId;
    private float rise;
    private float set;
    private boolean toBeObserved = true;
    private ArrayList<Coordinate> coverage = new ArrayList<Coordinate>();

    public String getGridId() {
        return gridId;
    }

    public void setGridId(String gridId) {
        this.gridId = gridId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = Long.parseLong(id);
    }

    public long getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(long configurationId) {
        this.configurationId = configurationId;
    }

    public Coordinate getTarget() {
        return target;
    }

    public void setTarget(Coordinate target) {
        this.target = target;
    }

    public void setTarget(String target) {
        this.target = new Coordinate(target);
    }

    public float getRise() {
        return rise;
    }

    public void setRise(float rise) {
        this.rise = rise;
    }

    public float getSet() {
        return set;
    }

    public void setSet(float set) {
        this.set = set;
    }

    public boolean getToBeObserved() {
        return toBeObserved;
    }

    public void setToBeObserved(boolean toBeObserved) {
        this.toBeObserved = toBeObserved;
    }

    public ArrayList<Coordinate> getCoverage() {
        return coverage;
    }

    public void addToCoverage(Coordinate coord) {
        coverage.add(coord);
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

    public ComplicatedElementHandler getComplicatedElementHandler(String type) {
        return new PointingCoordinateComplicatedElementHandler();
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
        xmlParameters.put("GridId", StringConvertable.STRING);
        xmlParameters.put("Target", StringConvertable.COORDINATE);
        xmlParameters.put("ConfigurationId", StringConvertable.ID);
        xmlParameters.put("Rise", StringConvertable.FLOAT);
        xmlParameters.put("Set", StringConvertable.FLOAT);
        xmlParameters.put("ToBeObserved", StringConvertable.BOOLEAN);


        xmlComplexElements.add("ExpectedCoverage");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IdAble) {
            return ((IdAble) obj).getId() == this.id;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new Long(this.id).hashCode();
    }

    public Coordinate getCoordinate() {
        return this.getTarget();
    }

    private class PointingCoordinateComplicatedElementHandler implements ComplicatedElementHandler<Pointing> {

        private Pointing item;

        public void setParent(Pointing item) {
            this.item = item;
        }
        
        
        

        
        public List<String> createXMLLines() {
            ArrayList<String> out = new ArrayList<String>();
            out.add("<expected_coverage>");
            for (Coordinate c : item.getCoverage()) {
                out.add("\t<coordinate>" + c.toString(true) + "</coordinate>");
            }
            out.add("</expected_coverage>");

            return out;
        }

        public void endTag(String localname, StringBuffer content) throws SAXException {
            if (localname.equals("coordinate")) {
                Coordinate coord = new Coordinate(content.toString());
                item.addToCoverage(coord);
            }
        }

        public void startTag(String localname, Map<String, String> attribites) throws SAXException {
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Pointing ret = new Pointing();
        ret.configurationId = configurationId;
        ret.coverage.addAll(coverage);
        ret.gridId = gridId;
        ret.id = id;
        ret.rise = rise;
        ret.set = set;
        ret.target = target;
        ret.toBeObserved = toBeObserved;
        return ret;
    }
}
