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
public class Telescope implements XMLAble, IdAble {

    private coordlib.Telescope coordLibTelescope;
    private String name;
    private float zenithLimit = 0;
    private float lattitude = 0;
    private double x,  y,  z;
    private float longitude = 0;
    private int sigprocCode;
    private String tempoCode;
    private String pulsarhunterCode;
    private long id = 0;

    public coordlib.Telescope getCoordLibTelescope() {
        return coordLibTelescope;
    }

    public void setCoordLibTelescope(coordlib.Telescope coordLibTelescope) {
        this.coordLibTelescope = coordLibTelescope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public String getPulsarhunterCode() {
        return pulsarhunterCode;
    }

    public void setPulsarhunterCode(String pulsarhunterCode) {
        this.pulsarhunterCode = pulsarhunterCode;
        try {
            this.coordLibTelescope = coordlib.Telescope.valueOf(pulsarhunterCode.toUpperCase());
        } catch (Exception e) {
            this.coordLibTelescope = coordlib.Telescope.UNKNOWN;
        }
    }

    public int getSigprocCode() {
        return sigprocCode;
    }

    public void setSigprocCode(int sigprocCode) {
        this.sigprocCode = sigprocCode;
    }

    public String getTempoCode() {
        return tempoCode;
    }

    public void setTempoCode(String tempoCode) {
        this.tempoCode = tempoCode;
    }

    public float getLattitude() {
        return lattitude;
    }

    public void setLattitude(float lattitude) {
        this.lattitude = lattitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getZenithLimit() {
        return zenithLimit;
    }

    public void setZenithLimit(float zenithLimit) {
        this.zenithLimit = zenithLimit;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 17 * hash + Float.floatToIntBits(this.zenithLimit);
        hash = 17 * hash + Float.floatToIntBits(this.lattitude);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        hash = 17 * hash + Float.floatToIntBits(this.longitude);
        return hash;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Telescope other = (Telescope) obj;
        if (this.name != other.name && (this.name == null || !this.name.equals(other.name))) {
            return false;
        }
        if (this.zenithLimit != other.zenithLimit) {
            return false;
        }
        if (this.lattitude != other.lattitude) {
            return false;
        }
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.z != other.z) {
            return false;
        }
        if (this.longitude != other.longitude) {
            return false;
        }
        return true;
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
        xmlParameters.put("ZenithLimit", StringConvertable.ANGLE);
        xmlParameters.put("Longitude", StringConvertable.ANGLE);
        xmlParameters.put("Lattitude", StringConvertable.ANGLE);
        xmlParameters.put("X", StringConvertable.DOUBLE);
        xmlParameters.put("Y", StringConvertable.DOUBLE);
        xmlParameters.put("Z", StringConvertable.DOUBLE);
        xmlParameters.put("SigprocCode", StringConvertable.INT);
        xmlParameters.put("TempoCode", StringConvertable.STRING);
        xmlParameters.put("PulsarhunterCode", StringConvertable.STRING);

    }
}
