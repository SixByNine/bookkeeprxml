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


import bookkeepr.xml.IdAble;
import bookkeepr.xml.StringConvertable;

import bookkeepr.xml.XMLAble;
import coordlib.Coordinate;
import coordlib.SkyLocated;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.util.DateParser;
import org.w3c.util.InvalidDateException;

/**
 *
 * @author kei041
 */
public class Observation implements XMLAble, IdAble, SkyLocated {

    private static Coordinate DEFAULT_COORDINATE = new Coordinate(0, 0);
    private long id = 0;
    long pointingId;
    Coordinate position = DEFAULT_COORDINATE;
    int beamNumber;
    float tobs;
    double mjd;
    Date utc;

    public int getBeamNumber() {
        return beamNumber;
    }

    public void setBeamNumber(int beamNumber) {
        this.beamNumber = beamNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPointingId() {
        return pointingId;
    }

    public void setPointingId(long pointingId) {
        this.pointingId = pointingId;
    }

    public Coordinate getPosition() {
        position.cleanRA();
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public double getMjd() {
        return mjd;
    }

    public void setMjd(double mjd) {
        this.mjd = mjd;
    }

    public float getTobs() {
        return tobs;
    }

    public void setTobs(float tobs) {
        this.tobs = tobs;
    }

    public String getUtc() {
        if (utc == null) {
            return null;
        }
        return DateParser.getIsoDateNoMillis(utc);
    }

    public void setUtc(String utcStr) {
        try {
            this.utc = DateParser.parse(utcStr);
        } catch (InvalidDateException ex) {
            Logger.getLogger(Observation.class.getName()).log(Level.WARNING, "Bad date/time specified in psrxml file. MUST be in ISO8601 format.", ex);
            this.utc = null;
        }
    }

    public Date getDate() {
        return this.utc;
    }

    public void setDate(Date date) {
        this.utc = date;
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
        xmlParameters.put("PointingId", StringConvertable.ID);
        xmlParameters.put("Position", StringConvertable.COORDINATE);
        xmlParameters.put("BeamNumber", StringConvertable.INT);
        xmlParameters.put("Tobs", StringConvertable.FLOAT);
        xmlParameters.put("Mjd", StringConvertable.DOUBLE);

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
        return this.getPosition();
    }
}
