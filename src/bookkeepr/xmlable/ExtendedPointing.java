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
import coordlib.Coordinate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kei041
 */
public class ExtendedPointing extends Pointing {

    ArrayList<Psrxml> observations = new ArrayList<Psrxml>();
    String raStr;
    String decStr;
    String glStr;
    String gbStr;
    String scheduleLine;
    float tobs;

    public ExtendedPointing() {
    }

    public ExtendedPointing(Pointing ptg) {
        this.setId(ptg.getId());
        this.setRise(ptg.getRise());
        this.setSet(ptg.getSet());
        this.setTarget(ptg.getTarget());
        this.setToBeObserved(ptg.getToBeObserved());
        this.setGridId(ptg.getGridId());
        this.setConfigurationId(ptg.getConfigurationId());
        for (Coordinate c : ptg.getCoverage()) {
            this.addToCoverage(c);
        }
    }

    public void addPsrxml(Psrxml obj) {
        observations.add(obj);
    }

    public List<Psrxml> getPsrxmlList() {
        return observations;
    }

    public void setObservations(ArrayList<Psrxml> observations) {
        this.observations = observations;
    }

    public String getDecStr() {
        return decStr;
    }

    public void setDecStr(String decStr) {
        this.decStr = decStr;
    }

    public String getGbStr() {
        return gbStr;
    }

    public void setGbStr(String gbStr) {
        this.gbStr = gbStr;
    }

    public String getGlStr() {
        return glStr;
    }

    public void setGlStr(String glStr) {
        this.glStr = glStr;
    }

    public String getRaStr() {
        return raStr;
    }

    public void setRaStr(String raStr) {
        this.raStr = raStr;
    }

    public float getTobs() {
        return tobs;
    }

    public void setTobs(float tobs) {
        this.tobs = tobs;
    }

    public String getScheduleLine() {
        return scheduleLine;
    }

    public void setScheduleLine(String scheduleLine) {
        this.scheduleLine = scheduleLine;
    }

    
    
    
    
    @Override
    public void setTarget(Coordinate target) {
        super.setTarget(target);
        this.setRaStr(target.getRA().toString(false));
        this.setDecStr(target.getDec().toString(false));
        StringBuffer buf = new StringBuffer();
        Formatter f = new Formatter(buf);
        f.format("%3.2f", target.getGl());
        this.setGlStr(buf.toString());
        buf.setLength(0);
        f.format("%3.2f", target.getGb());
        this.setGbStr(buf.toString());
    }
    protected static HashMap<String, StringConvertable> xmlParameters2 = new HashMap<String, StringConvertable>();
    protected static ArrayList<String> xmlSubObjects2 = new ArrayList<String>();

    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    public HashMap<String, StringConvertable> getXmlParameters() {
        return xmlParameters2;
    }

    public List<String> getXmlSubObjects() {
        return xmlSubObjects2;
    }
    

    static {


        xmlSubObjects2 = (ArrayList<String>) xmlSubObjects.clone();
        xmlSubObjects2.add("Psrxml");
        xmlParameters2 = (HashMap<String, StringConvertable>) xmlParameters.clone();
        xmlParameters2.put("RaStr", StringConvertable.STRING);
        xmlParameters2.put("DecStr", StringConvertable.STRING);
        xmlParameters2.put("GlStr", StringConvertable.STRING);
        xmlParameters2.put("GbStr", StringConvertable.STRING);
        xmlParameters2.put("Tobs", StringConvertable.FLOAT);
        xmlParameters2.put("ScheduleLine", StringConvertable.STRING);

    }
}
