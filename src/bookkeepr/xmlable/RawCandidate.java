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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kei041
 */
public class RawCandidate extends RawCandidateStub {

    private HashMap<String, RawCandidateSection> sections = new HashMap<String, RawCandidateSection>();
    private ArrayList<String> keys = new ArrayList<String>();
    
    private Coordinate coordinate=null;
    private String telescope;
    private float centreFreq;
    private float bandwidth;
    private double mjdStart;
    private Date utc;
    private double tobs;
    private RawCandidateSection initialSec=null,optimisedSec=null;

    
    private String sourceId;
    private float spectralSnr;
    private float reconstructedSnr;

    public float getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(float bandwidth) {
        this.bandwidth = bandwidth;
    }

    public float getCentreFreq() {
        return centreFreq;
    }

    public void setCentreFreq(float centreFreq) {
        this.centreFreq = centreFreq;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public ArrayList<String> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<String> keys) {
        this.keys = keys;
    }

    public double getMjdStart() {
        return mjdStart;
    }

    public void setMjdStart(double mjdStart) {
        this.mjdStart = mjdStart;
    }

    public HashMap<String, RawCandidateSection> getSections() {
        return sections;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getTelescope() {
        return telescope;
    }

    public void setTelescope(String telescope) {
        this.telescope = telescope;
    }

    public double getTobs() {
        return tobs;
    }

    public void setTobs(double tobs) {
        this.tobs = tobs;
    }

    public Date getUtc() {
        return utc;
    }

    public void setUtc(Date utc) {
        this.utc = utc;
    }

    public float getReconstructedSnr() {
        return reconstructedSnr;
    }

    public void setReconstructedSnr(float reconstructedSnr) {
        this.reconstructedSnr = reconstructedSnr;
    }

    public float getSpectralSnr() {
        return spectralSnr;
    }

    public void setSpectralSnr(float spectralSnr) {
        this.spectralSnr = spectralSnr;
    }

    public RawCandidateSection getInitialSec() {
        return initialSec;
    }

    public RawCandidateSection getOptimisedSec() {
        return optimisedSec;
    }

    
    
    
    
    
    public void addRawCandidateSection(RawCandidateSection section) {
        this.keys.add(section.getName());
        this.sections.put(section.getName(), section);
        this.optimisedSec = section;
        if(this.initialSec==null)this.initialSec = section;
    }

    public List<RawCandidateSection> getRawCandidateSectionList() {
        ArrayList<RawCandidateSection> ret = new ArrayList<RawCandidateSection>();
        for (String key : keys) {
            ret.add(sections.get(key));
        }
        return ret;
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
        xmlParameters.put("CandidateListId", StringConvertable.ID);
        xmlParameters.put("Coordinate", StringConvertable.COORDINATE);
        xmlParameters.put("CentreFreq", StringConvertable.FREQ);
        xmlParameters.put("Bandwidth", StringConvertable.FREQ);
        xmlParameters.put("MjdStart", StringConvertable.DOUBLE);
        xmlParameters.put("Utc", StringConvertable.ISODATE);
        xmlParameters.put("Tobs", StringConvertable.TIME);
        xmlParameters.put("SourceId", StringConvertable.STRING);
        xmlParameters.put("ReconstructedSnr", StringConvertable.FLOAT);
        xmlParameters.put("SpectralSnr", StringConvertable.FLOAT);

        
        
        xmlSubObjects.add("RawCandidateSection");
    }
}