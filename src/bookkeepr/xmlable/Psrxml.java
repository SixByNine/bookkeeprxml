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
import bookkeepr.xml.XMLWriter;
import coordlib.Coordinate;
import coordlib.Dec;
import coordlib.RA;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xml.sax.SAXException;

/**
 *
 * @author kei041
 */
public class Psrxml implements XMLAbleComplex, IdAble {

    private long id = 0;
    private long pointingId = 0;
    private int headerVersion = 0;
    private String sourceName;
    private String sourceNameCentreBeam;
    private String catReference;
    private int DayOfObservation;
    private long midnightToFirstSample;
    private String utc;
    private Date approxDate = null;
    private String lst;
    private String localTime;
    private double nativeSampleRate = Double.NaN;
    private int numberOfSamples;
    private double requestedObsTime = Double.NaN;
    private double currentSampleInterval;
    private double actualObsTime;
    private float centreFreqFirstChannel;
    private int numberOfChannels;
    private float channelOffset;
    private int receiverBeam;
    private ArrayList<Data> dataFiles = new ArrayList<Data>();
    private PsrxmlCoordinate startCoordinate;
    private PsrxmlCoordinate endCoordinate;
    private PsrxmlCoordinate requestedCoordinate;
    private float startParalacticAngle = Float.NaN;
    private float endParalacticAngle = Float.NaN;
    private boolean paralacticAngleTracking = false;
    private float startAz = Float.NaN;
    private float endAz = Float.NaN;
    private float startEl = Float.NaN;
    private float endEl = Float.NaN;
    private String observingProgramme;
    private String observerName;
    private String observationType;
    private String observationConfiguration;
    private String backendIdentifyingString;
    private String receiverIdentifyingString;
    private String telescopeIdentifyingString;
    private String backendConfigString;
    private String receiverConfigString;
    private String telescopeConfigString;
    private Telescope telescope;
    private Receiver receiver;
    private Backend backend;
    private int totalBeamsRecorded;
    private String recordedPolarisations;
    private String comment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getPointingId() {
        return pointingId;
    }

    public void setPointingId(long pointingId) {
        this.pointingId = pointingId;
    }

    public double getActualObsTime() {
        return actualObsTime;
    }

    public void setActualObsTime(double actualObsTime) {
        this.actualObsTime = actualObsTime;
    }

    public float getCentreFreqFirstChannel() {
        return centreFreqFirstChannel;
    }

    public void setCentreFreqFirstChannel(float centreFreqFirstChannel) {
        this.centreFreqFirstChannel = centreFreqFirstChannel;
    }

    public float getChannelOffset() {
        return channelOffset;
    }

    public void setChannelOffset(float channelOffset) {
        this.channelOffset = channelOffset;
    }

    public double getCurrentSampleInterval() {
        return currentSampleInterval;
    }

    public void setCurrentSampleInterval(double currentSampleInterval) {
        this.currentSampleInterval = currentSampleInterval;
    }

    public int getDayOfObservation() {
        return DayOfObservation;
    }

    public void setDayOfObservation(int DayOfObservation) {
        this.DayOfObservation = DayOfObservation;
    }

    public long getMidnightToFirstSample() {
        return midnightToFirstSample;
    }

    public void setMidnightToFirstSample(long midnightToFirstSample) {
        this.midnightToFirstSample = midnightToFirstSample;
    }

    public int getNumberOfChannels() {
        return numberOfChannels;
    }

    public void setNumberOfChannels(int numberOfChannels) {
        this.numberOfChannels = numberOfChannels;
    }

    public int getReceiverBeam() {
        return receiverBeam;
    }

    public void setReceiverBeam(int receiverBeam) {
        this.receiverBeam = receiverBeam;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public int getNumberOfSamples() {
        return numberOfSamples;
    }

    public void setNumberOfSamples(int numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }

    public void addData(Data data) {
        this.dataFiles.add(data);
    }

    public List<Data> getDataList() {
        return this.dataFiles;
    }

    public PsrxmlCoordinate getEndCoordinate() {
        return endCoordinate;
    }

    public void setEndCoordinate(PsrxmlCoordinate endCoordinate) {
        this.endCoordinate = endCoordinate;
    }

    public PsrxmlCoordinate getStartCoordinate() {
        return startCoordinate;
    }

    public void setStartCoordinate(PsrxmlCoordinate startCoordinate) {
        this.startCoordinate = startCoordinate;
    }

    public PsrxmlCoordinate getRequestedCoordinate() {
        return requestedCoordinate;
    }

    public void setRequestedCoordinate(PsrxmlCoordinate requestedPosition) {
        this.requestedCoordinate = requestedPosition;
    }

    public String getCatReference() {
        return catReference;
    }

    public void setCatReference(String catReference) {
        this.catReference = catReference;
    }

    public ArrayList<Data> getDataFiles() {
        return dataFiles;
    }

    public void setDataFiles(ArrayList<Data> dataFiles) {
        this.dataFiles = dataFiles;
    }

    public int getHeaderVersion() {
        return headerVersion;
    }

    public void setHeaderVersion(int headerVersion) {
        this.headerVersion = headerVersion;
    }

    public double getNativeSampleRate() {
        return nativeSampleRate;
    }

    public void setNativeSampleRate(double nativeSampleRate) {
        this.nativeSampleRate = nativeSampleRate;
    }

    public String getObservationType() {
        return observationType;
    }

    public void setObservationType(String observationType) {
        this.observationType = observationType;
    }

    public String getObserverName() {
        return observerName;
    }

    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }

    public String getObservingProgramme() {
        return observingProgramme;
    }

    public void setObservingProgramme(String observingProgramme) {
        this.observingProgramme = observingProgramme;
    }

    public String getObservationConfiguration() {
        return observationConfiguration;
    }

    public void setObservationConfiguration(String observationConfiguration) {
        this.observationConfiguration = observationConfiguration;
    }

    public boolean getParalacticAngleTracking() {
        return paralacticAngleTracking;
    }

    public void setParalacticAngleTracking(boolean paralacticAngleTracking) {
        this.paralacticAngleTracking = paralacticAngleTracking;
    }

    public double getRequestedObsTime() {
        return requestedObsTime;
    }

    public void setRequestedObsTime(double requestedObsTime) {
        this.requestedObsTime = requestedObsTime;
    }

    public String getSourceNameCentreBeam() {
        return sourceNameCentreBeam;
    }

    public void setSourceNameCentreBeam(String sourceNameCentreBeam) {
        this.sourceNameCentreBeam = sourceNameCentreBeam;
    }

    public float getEndAz() {
        return endAz;
    }

    public void setEndAz(float endAz) {
        this.endAz = endAz;
    }

    public float getEndEl() {
        return endEl;
    }

    public void setEndEl(float endEl) {
        this.endEl = endEl;
    }

    public float getEndParalacticAngle() {
        return endParalacticAngle;
    }

    public void setEndParalacticAngle(float endParalacticAngle) {
        this.endParalacticAngle = endParalacticAngle;
    }

    public float getStartAz() {
        return startAz;
    }

    public void setStartAz(float startAz) {
        this.startAz = startAz;
    }

    public float getStartEl() {
        return startEl;
    }

    public void setStartEl(float startEl) {
        this.startEl = startEl;
    }

    public float getStartParalacticAngle() {
        return startParalacticAngle;
    }

    public void setStartParalacticAngle(float startParalacticAngle) {
        this.startParalacticAngle = startParalacticAngle;
    }

    public String getRecordedPolarisations() {
        return recordedPolarisations;
    }

    public void setRecordedPolarisations(String recordedPolarisations) {
        this.recordedPolarisations = recordedPolarisations;
    }

    public int getTotalBeamsRecorded() {
        return totalBeamsRecorded;
    }

    public void setTotalBeamsRecorded(int totalBeamsRecorded) {
        this.totalBeamsRecorded = totalBeamsRecorded;
    }

    public Backend getBackend() {
        return backend;
    }

    public void setBackend(Backend backend) {
        this.backend = backend;
    }

    public List<Backend> getBackendList() {
        ArrayList<Backend> bac = new ArrayList<Backend>();
        bac.add(backend);
        return bac;
    }

    public void addBackend(Backend backend) {
        this.backend = backend;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public List<Receiver> getReceiverList() {
        ArrayList<Receiver> rx = new ArrayList<Receiver>();
        rx.add(receiver);
        return rx;
    }

    public void addReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public Telescope getTelescope() {
        return telescope;
    }

    public void setTelescope(Telescope telescope) {
        this.telescope = telescope;
    }

    public List<Telescope> getTelescopeList() {
        ArrayList<Telescope> tel = new ArrayList<Telescope>();
        tel.add(telescope);
        return tel;
    }

    public void addTelescope(Telescope telescope) {
        this.telescope = telescope;
    }

    public String getBackendConfigString() {
        return backendConfigString;
    }

    public void setBackendConfigString(String backendConfigString) {
        this.backendConfigString = backendConfigString;
    }

    public String getBackendIdentifyingString() {
        return backendIdentifyingString;
    }

    public void setBackendIdentifyingString(String backendIdentifyingString) {
        this.backendIdentifyingString = backendIdentifyingString;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    public String getLst() {
        return lst;
    }

    public void setLst(String lst) {
        this.lst = lst;
    }

    public String getReceiverConfigString() {
        return receiverConfigString;
    }

    public void setReceiverConfigString(String receiverConfigString) {
        this.receiverConfigString = receiverConfigString;
    }

    public String getReceiverIdentifyingString() {
        return receiverIdentifyingString;
    }

    public void setReceiverIdentifyingString(String receiverIdentifyingString) {
        this.receiverIdentifyingString = receiverIdentifyingString;
    }

    public String getTelescopeConfigString() {
        return telescopeConfigString;
    }

    public void setTelescopeConfigString(String telescopeConfigString) {
        this.telescopeConfigString = telescopeConfigString;
    }

    public String getTelescopeIdentifyingString() {
        return telescopeIdentifyingString;
    }

    public void setTelescopeIdentifyingString(String telescopeIdentifyingString) {
        this.telescopeIdentifyingString = telescopeIdentifyingString;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }

    public Date getApproxDate() {
        if (approxDate == null) {
            approxDate = StringConvertable.ISODATE.fromString(utc);
        }
        return approxDate;
    }

    /* BEGIN XMLABLE STUFF */
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
        if (type.equals("StartCoordinate") || type.equals("EndCoordinate") || type.equals("RequestedCoordinate")) {
            return new PsrxmlCoordinateComplicatedElementHandler(type);
        }
        if (type.equals("StartTelescopePosition") || type.equals("EndTelescopePosition")) {
            return new PsrxmlAzElComplicatedElementHandler(type);
        }
        throw new UnsupportedOperationException("Not supported yet.");

    }

    public Map<String, String> getXmlAttributes() {
        HashMap<String, String> attr = new HashMap<String, String>();
        attr.put("version", String.valueOf(this.headerVersion));
        return attr;
    }

    public List<String> getXmlComplexElements() {
        return xmlComplexElements;
    }

    public void setXmlAttributes(Map<String, String> attr) {
        String strV = attr.get("version");
        if (strV != null) {
            this.headerVersion = Integer.parseInt(strV);
        }
    }
    protected static HashMap<String, StringConvertable> xmlParameters = new HashMap<String, StringConvertable>();
    protected static ArrayList<String> xmlSubObjects = new ArrayList<String>();
    protected static ArrayList<String> xmlComplexElements = new ArrayList<String>();
    

    static {


        xmlParameters.put("Id", StringConvertable.ID);
        xmlParameters.put("PointingId", StringConvertable.ID);


        xmlParameters.put("SourceName", StringConvertable.STRING);
        xmlParameters.put("SourceNameCentreBeam", StringConvertable.STRING);
        xmlParameters.put("CatReference", StringConvertable.STRING);

        xmlParameters.put("DayOfObservation", StringConvertable.MJD);
        xmlParameters.put("MidnightToFirstSample", StringConvertable.NANOSECLONG);
        xmlParameters.put("CurrentSampleInterval", StringConvertable.TIME);
        xmlParameters.put("NativeSampleRate", StringConvertable.TIME);
        xmlParameters.put("NumberOfSamples", StringConvertable.INT);

        xmlParameters.put("RequestedObsTime", StringConvertable.TIME);
        xmlParameters.put("ActualObsTime", StringConvertable.TIME);
        xmlParameters.put("CentreFreqFirstChannel", StringConvertable.FREQ);
        xmlParameters.put("NumberOfChannels", StringConvertable.INT);
        xmlParameters.put("ChannelOffset", StringConvertable.FREQ);
        xmlParameters.put("ReceiverBeam", StringConvertable.INT);

        xmlParameters.put("StartParalacticAngle", StringConvertable.ANGLE);
        xmlParameters.put("EndParalacticAngle", StringConvertable.ANGLE);
        xmlParameters.put("ParalacticAngleTracking", StringConvertable.BOOLEAN);

        xmlParameters.put("ObservingProgramme", StringConvertable.STRING);
        xmlParameters.put("ObserverName", StringConvertable.STRING);
        xmlParameters.put("ObservationType", StringConvertable.STRING);
        xmlParameters.put("ObservationConfiguration", StringConvertable.STRING);

        xmlParameters.put("TotalBeamsRecorded", StringConvertable.INT);
        xmlParameters.put("RecordedPolarisations", StringConvertable.STRING);

        xmlParameters.put("Lst", StringConvertable.STRING);
        xmlParameters.put("Utc", StringConvertable.STRING);
        xmlParameters.put("LocalTime", StringConvertable.STRING);

        xmlParameters.put("Comment", StringConvertable.STRING);


        xmlSubObjects.add("Data");
        xmlSubObjects.add("Receiver");
        xmlSubObjects.add("Telescope");
        xmlSubObjects.add("Backend");

        xmlComplexElements.add("StartCoordinate");
        xmlComplexElements.add("EndCoordinate");

        xmlComplexElements.add("StartTelescopePosition");
        xmlComplexElements.add("EndTelescopePosition");

        xmlComplexElements.add("RequestedCoordinate");


    }

    public class PsrxmlCoordinate extends Coordinate {

        private String epoch = null;
        private double error = Double.NaN;

        public PsrxmlCoordinate(RA ra, Dec dec) {
            super(ra, dec);
        }

        public PsrxmlCoordinate(double l, double b) {
            super(l, b);
        }

        public PsrxmlCoordinate() {
        }

        /**
         * Get the value of error
         *
         * @return the value of error
         */
        public double getError() {
            return error;
        }

        /**
         * Set the value of error
         *
         * @param error new value of error
         */
        public void setError(double error) {
            this.error = error;
        }

        /**
         * Get the value of epoch
         *
         * @return the value of epoch
         */
        public String getEpoch() {
            return epoch;
        }

        /**
         * Set the value of epoch
         *
         * @param epoch new value of epoch
         */
        public void setEpoch(String epoch) {
            this.epoch = epoch;
        }
    }

    private class PsrxmlCoordinateComplicatedElementHandler implements ComplicatedElementHandler<Psrxml> {

        private Psrxml psrxml;
        private double ra = 0;
        private double dec = 0;
        private String epoch = null;
        private double error = Double.NaN;
        private String tagname;
        private Map<String, Map<String, String>> attr = new HashMap<String, Map<String, String>>();

        public PsrxmlCoordinateComplicatedElementHandler(String tagname) {
            this.tagname = tagname;
        }

        public void endTag(String localname, StringBuffer content) throws SAXException {
            Map<String, String> attributes = attr.get(localname);
            if (localname.equals("ra")) {
                this.ra = StringConvertable.ANGLE.fromString(content.toString(), attributes);
            } else if (localname.equals("dec")) {
                this.dec = StringConvertable.ANGLE.fromString(content.toString(), attributes);
            } else if (localname.equals("position_epoch")) {
                this.epoch = StringConvertable.STRING.fromString(content.toString(), attributes);
            } else if (localname.equals("possition_error")) {
                this.error = StringConvertable.DOUBLE.fromString(content.toString(), attributes);
            } else if (localname.equals("start_coordinate")) {
                PsrxmlCoordinate coord = new PsrxmlCoordinate(new RA(ra), new Dec(dec));
                coord.setEpoch(epoch);
                coord.setError(error);
                psrxml.setStartCoordinate(coord);
            } else if (localname.equals("end_coordinate")) {
                PsrxmlCoordinate coord = new PsrxmlCoordinate(new RA(ra), new Dec(dec));
                coord.setEpoch(epoch);
                coord.setError(error);
                psrxml.setEndCoordinate(coord);
            } else if (localname.equals("requested_coordinate")) {
                PsrxmlCoordinate coord = new PsrxmlCoordinate(new RA(ra), new Dec(dec));
                coord.setEpoch(epoch);
                coord.setError(error);
                psrxml.setRequestedCoordinate(requestedCoordinate);
            }
        }

        public void setParent(Psrxml item) {
            this.psrxml = item;
        }

        public void startTag(String localname, Map attribites) throws SAXException {
            attr.put(localname, attribites);
        }

        public List<String> createXMLLines() {
            PsrxmlCoordinate coord = null;
            if (tagname.equals("StartCoordinate")) {
                coord = psrxml.getStartCoordinate();
            } else if (tagname.equals("EndCoordinate")) {
                coord = psrxml.getEndCoordinate();
            } else if (tagname.equals("RequestedCoordinate")) {
                coord = psrxml.getRequestedCoordinate();
            }

            ArrayList<String> out = new ArrayList<String>();
            if (coord != null) {
                out.add("<" + XMLWriter.mangleName(tagname) + ">");
                out.add("\t<coordinate>");
                out.add("\t\t<ra units='degrees'>" + coord.getRA().toDegrees() + "</ra>");
                out.add("\t\t<dec units='degrees'>" + coord.getDec().toDegrees() + "</dec>");
                if (coord.getEpoch() != null) {
                    out.add("\t\t<position_epoch>" + coord.getEpoch() + "</position_epoch>");
                }
                if (!Double.isNaN(coord.getError())) {
                    out.add("\t\t<position_error>" + coord.getError() + "</position_error>");
                }
                out.add("\t\t<friendly_eq>" + coord.toString(false) + "</friendly_eq>");

                out.add("\t\t<friendly_gal>" + coord.toString(true) + "</friendly_gal>");

                out.add("\t</coordinate>");
                out.add("</" + XMLWriter.mangleName(tagname) + ">");
            }
            return out;
        }
    }

    private class PsrxmlAzElComplicatedElementHandler implements ComplicatedElementHandler<Psrxml> {

        private Psrxml psrxml;
        private float az = 0;
        private float el = 0;
        private String tagname;
        private Map<String, Map<String, String>> attr = new HashMap<String, Map<String, String>>();

        public PsrxmlAzElComplicatedElementHandler(String tagname) {
            this.tagname = tagname;
        }

        public void endTag(String localname, StringBuffer content) throws SAXException {
            Map<String, String> attributes = attr.get(localname);
            if (localname.equals("az")) {
                this.az = StringConvertable.ANGLE.fromString(content.toString(), attributes);
            } else if (localname.equals("el")) {
                this.el = StringConvertable.ANGLE.fromString(content.toString(), attributes);
            } else if (localname.equals("start_telescope_position")) {

                psrxml.setStartAz(az);
                psrxml.setStartEl(el);
            } else if (localname.equals("end_telescope_position")) {
                psrxml.setEndAz(az);
                psrxml.setEndEl(el);
            }
        }

        public void setParent(Psrxml item) {
            this.psrxml = item;
        }

        public void startTag(String localname, Map attribites) throws SAXException {
            attr.put(localname, attribites);
        }

        public List<String> createXMLLines() {
            if (tagname.equals("StartTelescopePosition")) {
                az = psrxml.getStartAz();
                el = psrxml.getStartEl();
            } else if (tagname.equals("EndTelescopePosition")) {
                az = psrxml.getEndAz();
                el = psrxml.getEndEl();
            }

            ArrayList<String> out = new ArrayList<String>();
            out.add("<" + XMLWriter.mangleName(tagname) + ">");

            out.add("\t<az units='degrees'>" + az + "</az>");
            out.add("\t<el units='degrees'>" + el + "</el>");

            out.add("</" + XMLWriter.mangleName(tagname) + ">");
            return out;
        }
    }
}
