/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bookkeepr.xmlable;

import bookkeepr.DummyIdAble;
import bookkeepr.xml.IdAble;
import bookkeepr.xml.StringConvertable;
import bookkeepr.xml.XMLAble;
import coordlib.Coordinate;
import coordlib.SkyLocated;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pulsarhunter.jreaper.CandClass;
import pulsarhunter.jreaper.HarmonicType;

/**
 *
 * @author kei041
 */
public class ClassifiedCandidate implements XMLAble, IdAble, SkyLocated {

    private long id = 0;
    private String name = null;
    private CandClass candClass = CandClass.Unclassified;
    private long originalRawCandId = 0;
//    private HashMap<HarmonicType, ArrayList<Long>> confirmedMatched = new HashMap<HarmonicType, ArrayList<Long>>();
//    private HashMap<HarmonicType, ArrayList<Long>> possibleMatches = new HashMap<HarmonicType, ArrayList<Long>>();
    private ArrayList<RawCandidateMatched> confirmedMatches = new ArrayList<RawCandidateMatched>();
    private ArrayList<RawCandidateMatched> possibleMatches = new ArrayList<RawCandidateMatched>();
    private Coordinate coordinate = null;
    private RawCandidateMatched preferedCandidate = null;
    private String obsStatus = "None";
    private String confStatus = "Unobserved";

    public ClassifiedCandidate() {
    }
    
    public StringBuffer getPsrcatEntry(){
        StringBuffer buf = new StringBuffer();
        RawCandidateMatched pref=getPreferedCandidate();
        buf.append("PSRJ     ");
        buf.append(name);
        buf.append("\n");
        buf.append("RAJ      ");
        buf.append(coordinate.getRA().toString(false));
        buf.append("\n");
        buf.append("DECJ     ");
        buf.append(coordinate.getDec().toString(false));
        buf.append("\n");
        buf.append("P0       ");
        buf.append(pref.getBaryPeriod());
        buf.append("\n");
        buf.append("DM       ");
        buf.append(pref.getDm());
        buf.append("\n");
        buf.append("PEPOCH   ");
        buf.append(pref.getMjd());
        buf.append("\n");
        return buf;
    }

    public CandClass getCandClass() {
        return candClass;
    }

    public List<Long> getMatched(HarmonicType htype, boolean b) {
        ArrayList<RawCandidateMatched> list = possibleMatches;
        if (b) {
            list = confirmedMatches;
        }


        ArrayList<Long> out = new ArrayList<Long>();

        for (RawCandidateMatched m : list) {
            if (m.getHarmonicType() == htype) {
                out.add(m.getId());
            }
        }
        return out;
    }

    public void setCandClass(CandClass candClass) {
        this.candClass = candClass;
        if (candClass.equals(CandClass.KnownPsr)) {
            this.confStatus = "KnownPsr";
            obsStatus = "None";
        }
    }

    public int getCandClassInt() {
        return candClass.getIntClass();
    }

    public void setCandClassInt(int candClass) {
        setCandClass(CandClass.fromIntClass(candClass));
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOriginalRawCandId() {
        return originalRawCandId;
    }

    public void setOriginalRawCandId(long originalRawCandId) {
        this.originalRawCandId = originalRawCandId;
    }

    public RawCandidateMatched getPreferedCandidate() {
        if (preferedCandidate == null) {
            if (this.confirmedMatches.size() > 0) {
                setPreferedCandidate(this.confirmedMatches.get(0));
            } else if (this.possibleMatches.size() > 0) {
                setPreferedCandidate(this.possibleMatches.get(0));
            }
        }
        return preferedCandidate;
    }

    public void setPreferedCandidate(RawCandidateMatched preferedCandidate) {
        preferedCandidate.setConfirmed(true);
        preferedCandidate.setPrefered(true);
        this.addRawCandidateMatched(preferedCandidate);
    }

    public RawCandidateMatched getRawCandidateMatched(long id) {
        int index = Collections.binarySearch(confirmedMatches, new DummyIdAble(id), IdAble.COMPARATOR);
        if (index < 0) {
        } else {
            return this.confirmedMatches.get(index);
        }
        index = Collections.binarySearch(possibleMatches, new DummyIdAble(id), IdAble.COMPARATOR);
        if (index < 0) {
        } else {
            return this.possibleMatches.get(index);
        }
        return null;
    }

    public void addRawCandidateMatched(RawCandidateMatched cand) {

        synchronized (confirmedMatches) {

            if (cand.getConfirmed()) {
                if (cand.getPrefered()) {
                    for (RawCandidateMatched m : this.confirmedMatches) {
                        m.setPrefered(false);
                    }
                    cand.setPrefered(true);
                    this.preferedCandidate = cand;
                }
                int index = Collections.binarySearch(confirmedMatches, cand, IdAble.COMPARATOR);
                if (index < 0) {
                    this.confirmedMatches.add(-index - 1, cand);
                } else {
                    this.confirmedMatches.remove(index);
                    this.confirmedMatches.add(index, cand);
                }
                index = Collections.binarySearch(possibleMatches, cand, IdAble.COMPARATOR);
                if (index < 0) {
                } else {
                    this.possibleMatches.remove(index);
                }

            } else {
                int index = Collections.binarySearch(confirmedMatches, cand, IdAble.COMPARATOR);
                if (index < 0) {
                } else {
                    this.confirmedMatches.remove(index);
                }
                index = Collections.binarySearch(possibleMatches, cand, IdAble.COMPARATOR);
                if (index < 0) {
                    this.possibleMatches.add(-index - 1, cand);
                } else {
                    this.possibleMatches.remove(index);
                    this.possibleMatches.add(index, cand);
                }
            }
        }
    }

    public List<RawCandidateMatched> getRawCandidateMatchedList() {
        ArrayList<RawCandidateMatched> ret = new ArrayList<RawCandidateMatched>(this.confirmedMatches);
        ret.addAll(this.possibleMatches);
        return ret;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getCandClass() + " " + this.getCoordinate();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ClassifiedCandidate c2 = new ClassifiedCandidate();

        c2.setCandClass(this.getCandClass());
        c2.setCoordinate(this.getCoordinate());
        c2.setId(this.getId());
        c2.setName(this.getName());
        c2.setOriginalRawCandId(this.getOriginalRawCandId());
        c2.setPreferedCandidate(this.getPreferedCandidate());
        c2.confirmedMatches = (ArrayList<RawCandidateMatched>) this.confirmedMatches.clone();
        c2.possibleMatches = (ArrayList<RawCandidateMatched>) this.possibleMatches.clone();

        return c2;
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
        /* private long id;
        private String name;
        private CandClass candClass;
        private long originalRawCandId;
        private HashMap<HarmonicType, Long> associatedDetections;
        private HashMap<HarmonicType, Long> possibleDetections;
        private Coordinate coordinate;
        private RawCandidateBasic preferedCandidate;
         */
        xmlParameters.put("Id", StringConvertable.ID);
        xmlParameters.put("Name", StringConvertable.STRING);
        xmlParameters.put("CandClassInt", StringConvertable.INT);
        xmlParameters.put("Coordinate", StringConvertable.COORDINATE);
        xmlParameters.put("ObsStatus", StringConvertable.STRING);
        xmlParameters.put("ConfStatus", StringConvertable.STRING);

        xmlSubObjects.add("RawCandidateMatched");
    }

    public String getObsStatus() {
        return obsStatus;
    }

    public void setObsStatus(String obsStatus) {
        this.obsStatus = obsStatus;
    }

    public String getConfStatus() {
        return confStatus;
    }

    public void setConfStatus(String confStatus) {
        this.confStatus = confStatus;
    }
}
