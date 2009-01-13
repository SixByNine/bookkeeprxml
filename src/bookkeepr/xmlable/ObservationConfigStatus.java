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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kei041
 */
public class ObservationConfigStatus implements XMLAble {

    private String name;
    private int totalPointings;
    private int remainingPointings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercentComplete() {
        return 100*(1.0-((double)remainingPointings/(double)totalPointings));
    }

    public void setPercentComplete(double percentComplete) {
    }

    public int getRemainingPointings() {
        return remainingPointings;
    }

    public void setRemainingPointings(int remainingPointings) {
        this.remainingPointings = remainingPointings;
    }

    public int getTotalPointings() {
        return totalPointings;
    }

    public void setTotalPointings(int totalPointings) {
        this.totalPointings = totalPointings;
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
        xmlParameters.put("Name", StringConvertable.STRING);
        xmlParameters.put("TotalPointings", StringConvertable.INT);
        xmlParameters.put("RemainingPointings", StringConvertable.INT);
        xmlParameters.put("PercentComplete", StringConvertable.DOUBLE);
    }
}
