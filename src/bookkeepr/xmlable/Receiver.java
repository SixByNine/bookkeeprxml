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
public class Receiver implements IdAble, XMLAble {

private long id=0;
private String name;
    private float fwhm;
    private float[] glBeamPattern;
    private float[] gbBeamPattern;
    private float[] glGridPattern;
    private float[] gbGridPattern;
    private float[] glGridOffset;
    private float[] gbGridOffset;
    private String feedPolarisationBasis; // linear or circular

    private String feedHandedness;
    private float calPhase;
    private float feedSymetry;
    private int numberOfPolarisations = 0;

    public Receiver() {
        this("", 0);
    }

    public Receiver(String name, float fwhm) {
        this.name = name;
        this.fwhm = fwhm;
        glBeamPattern = new float[]{0};
        gbBeamPattern = new float[]{0};
        glGridPattern = new float[]{0};
        gbGridPattern = new float[]{0};
        glGridOffset = new float[]{fwhm, 0};
        gbGridOffset = new float[]{fwhm, fwhm * 0.866025f};
    }

    public float getFwhm() {
        return fwhm;
    }

    public void setFwhm(float fwhm) {
        this.fwhm = fwhm;
    }

    public float[] getGbBeamPattern() {
        return gbBeamPattern;
    }

    public void setGbBeamPattern(float[] gbBeamPattern) {
        this.gbBeamPattern = gbBeamPattern;
    }

    public float[] getGbGridOffset() {
        return gbGridOffset;
    }

    public void setGbGridOffset(float[] gbGridOffset) {
        this.gbGridOffset = gbGridOffset;
    }

    public float[] getGbGridPattern() {
        return gbGridPattern;
    }

    public void setGbGridPattern(float[] gbGridPattern) {
        this.gbGridPattern = gbGridPattern;
    }

    public float[] getGlBeamPattern() {
        return glBeamPattern;
    }

    public void setGlBeamPattern(float[] glBeamPattern) {
        this.glBeamPattern = glBeamPattern;
    }

    public float[] getGlGridOffset() {
        return glGridOffset;
    }

    public void setGlGridOffset(float[] glGridOffset) {
        this.glGridOffset = glGridOffset;
    }

    public float[] getGlGridPattern() {
        return glGridPattern;
    }

    public void setGlGridPattern(float[] glGridPattern) {
        this.glGridPattern = glGridPattern;
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

    public String getFeedHandedness() {
        return feedHandedness;
    }

    public void setFeedHandedness(String feedHandedness) {
        this.feedHandedness = feedHandedness;
    }

    public String getFeedPolarisationBasis() {
        return feedPolarisationBasis;
    }

    public void setFeedPolarisationBasis(String feedPolarisationBasis) {
        this.feedPolarisationBasis = feedPolarisationBasis;
    }

    public float getCalPhase() {
        return calPhase;
    }

    public void setCalPhase(float calPhase) {
        this.calPhase = calPhase;
    }

    public float getFeedSymetry() {
        return feedSymetry;
    }

    public void setFeedSymetry(float feedSymetry) {
        this.feedSymetry = feedSymetry;
    }


    public int getNumberOfPolarisations() {
        return numberOfPolarisations;
    }

    public void setNumberOfPolarisations(int numberOfPolarisations) {
        this.numberOfPolarisations = numberOfPolarisations;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Receiver other = (Receiver) obj;
        if (this.name != other.name && (this.name == null || !this.name.equals(other.name))) {
            return false;
        }
        if (this.feedPolarisationBasis != other.feedPolarisationBasis && (this.feedPolarisationBasis == null || !this.feedPolarisationBasis.equals(other.feedPolarisationBasis))) {
            return false;
        }
        if (this.feedHandedness != other.feedHandedness && (this.feedHandedness == null || !this.feedHandedness.equals(other.feedHandedness))) {
            return false;
        }
        if (this.calPhase != other.calPhase) {
            return false;
        }
        if (this.feedSymetry != other.feedSymetry) {
            return false;
        }
        if (this.numberOfPolarisations != other.numberOfPolarisations) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 37 * hash + (this.feedPolarisationBasis != null ? this.feedPolarisationBasis.hashCode() : 0);
        hash = 37 * hash + (this.feedHandedness != null ? this.feedHandedness.hashCode() : 0);
        hash = 37 * hash + Float.floatToIntBits(this.calPhase);
        hash = 37 * hash + Float.floatToIntBits(this.feedSymetry);
        hash = 37 * hash + this.numberOfPolarisations;
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
        xmlParameters.put("Fwhm", StringConvertable.FLOAT);
        xmlParameters.put("GlBeamPattern", StringConvertable.FLOATARRAY);
        xmlParameters.put("GbBeamPattern", StringConvertable.FLOATARRAY);
        xmlParameters.put("GlGridPattern", StringConvertable.FLOATARRAY);
        xmlParameters.put("GbGridPattern", StringConvertable.FLOATARRAY);
        xmlParameters.put("GlGridOffset", StringConvertable.FLOATARRAY);
        xmlParameters.put("GbGridOffset", StringConvertable.FLOATARRAY);

        xmlParameters.put("FeedPolarisationBasis", StringConvertable.STRING);
        xmlParameters.put("FeedSymetry", StringConvertable.ANGLE);
        xmlParameters.put("FeedHandedness", StringConvertable.STRING);
        xmlParameters.put("CalPhase", StringConvertable.ANGLE);
        xmlParameters.put("NumberOfPolarisations", StringConvertable.INT);

    }
}
