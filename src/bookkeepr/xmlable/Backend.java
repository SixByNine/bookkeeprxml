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

import bookkeepr.xml.IdAble;
import bookkeepr.xml.StringConvertable;
import bookkeepr.xml.XMLAble;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kei041
 */
public class Backend implements XMLAble,IdAble {

private long id=0;
private String name;
    private String sideband;
    private String crossPhase;
    private int sigprocCode;

    public Backend() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrossPhase() {
        return crossPhase;
    }

    public void setCrossPhase(String crossPhase) {
        this.crossPhase = crossPhase;
    }

    public String getSideband() {
        return sideband;
    }

    public void setSideband(String sideband) {
        this.sideband = sideband;
    }

    public int getSigprocCode() {
        return sigprocCode;
    }

    public void setSigprocCode(int sigprocCode) {
        this.sigprocCode = sigprocCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Backend other = (Backend) obj;
        if (this.name != other.name && (this.name == null || !this.name.equals(other.name))) {
            return false;
        }
        if (this.sideband != other.sideband && (this.sideband == null || !this.sideband.equals(other.sideband))) {
            return false;
        }
        if (this.crossPhase != other.crossPhase && (this.crossPhase == null || !this.crossPhase.equals(other.crossPhase))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.sideband != null ? this.sideband.hashCode() : 0);
        hash = 29 * hash + (this.crossPhase != null ? this.crossPhase.hashCode() : 0);
        return hash;
    }
    
    
    

    @Override
    public String toString() {
        return this.getName();
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
        xmlParameters.put("Id", StringConvertable.ID);
        xmlParameters.put("Name", StringConvertable.STRING);
        xmlParameters.put("Sideband", StringConvertable.STRING);
        xmlParameters.put("CrossPhase", StringConvertable.STRING);
        xmlParameters.put("SigprocCode", StringConvertable.INT);

    }
}
