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

import bookkeepr.xml.ComplicatedElementHandler;
import bookkeepr.xml.HexLookup;
import bookkeepr.xml.StringConvertable;
import bookkeepr.xml.XMLAbleComplex;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xml.sax.SAXException;
import pulsarhunter.datatypes.SNRBlock;

/**
 *
 * @author kei041
 */
public class RawCandidateSection implements XMLAbleComplex {

    private String name;
    private EncodedTwoDimArray encodedSubintegrations = null;
    private EncodedTwoDimArray encodedSubbands = null;
    private EncodedOneDimArray encodedProfile = null;
    private SNRBlock snrBlock;
    private float bestTopoPeriod = Float.NaN;
    private float bestBaryPeriod = Float.NaN;
    private float bestDm = Float.NaN;
    private float bestAccn = Float.NaN;
    private float bestJerk = Float.NaN;
    private float bestSnr = Float.NaN;
    private float bestWidth = Float.NaN;
    private float tsamp = Float.NaN;
    private HashMap<String, String> extraValues = new HashMap<String, String>();

    public float getBestAccn() {
        return bestAccn;
    }

    public void setBestAccn(float bestAccn) {
        this.bestAccn = bestAccn;
    }

    public float getBestBaryPeriod() {
        return bestBaryPeriod;
    }

    public void setBestBaryPeriod(float bestBaryPeriod) {
        this.bestBaryPeriod = bestBaryPeriod;
    }

    public float getBestDm() {
        return bestDm;
    }

    public void setBestDm(float bestDm) {
        this.bestDm = bestDm;
    }

    public float getBestJerk() {
        return bestJerk;
    }

    public void setBestJerk(float bestJerk) {
        this.bestJerk = bestJerk;
    }

    public float getBestSnr() {
        return bestSnr;
    }

    public void setBestSnr(float bestSnr) {
        this.bestSnr = bestSnr;
    }

    public float getBestTopoPeriod() {
        return bestTopoPeriod;
    }

    public void setBestTopoPeriod(float bestTopoPeriod) {
        this.bestTopoPeriod = bestTopoPeriod;
    }

    public float getBestWidth() {
        return bestWidth;
    }

    public void setBestWidth(float bestWidth) {
        this.bestWidth = bestWidth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SNRBlock getSnrBlock() {
        return snrBlock;
    }

    public void setSnrBlock(SNRBlock snrBlock) {
        this.snrBlock = snrBlock;
    }

    public float getTsamp() {
        return tsamp;
    }

    public void setTsamp(float tsamp) {
        this.tsamp = tsamp;
    }

    public EncodedOneDimArray getEncodedProfile() {
        return encodedProfile;
    }

    public void setEncodedProfile(EncodedOneDimArray encodedProfile) {
        this.encodedProfile = encodedProfile;
    }

    public EncodedTwoDimArray getEncodedSubbands() {
        return encodedSubbands;
    }

    public void setEncodedSubbands(EncodedTwoDimArray encodedSubbands) {
        this.encodedSubbands = encodedSubbands;
    }

    public EncodedTwoDimArray getEncodedSubintegrations() {
        return encodedSubintegrations;
    }

    public void setEncodedSubintegrations(EncodedTwoDimArray encodedSubintegrations) {
        this.encodedSubintegrations = encodedSubintegrations;
    }

    public void setProfile(double[] profile) {
        if (profile == null) {
            this.encodedProfile = null;
        } else {
            this.encodedProfile = new EncodedOneDimArray();
            this.encodedProfile.setDoubleArr(profile);
        }
    }

    public void setSubIntegrations(double[][] subints) {
        if (subints == null) {
            this.encodedSubintegrations = null;
        } else {
            this.encodedSubintegrations = new EncodedTwoDimArray();
            this.encodedSubintegrations.setDoubleArr(subints);
        }
    }

    public void setSubBands(double[][] subbands) {
        if (subbands == null) {
            this.encodedSubbands = null;
        } else {

            this.encodedSubbands = new EncodedTwoDimArray();
            this.encodedSubbands.setDoubleArr(subbands);
        }
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

    public void setExtraValue(String key, String value) {
        this.extraValues.put(key, value);
    }

    public Map<String, String> getExtraMap() {
        return this.extraValues;
    }

    public ComplicatedElementHandler getComplicatedElementHandler(
            String type) {
        if (type.equals("ExtraValue")) {
            return new ComplicatedElementHandler<RawCandidateSection>() {

                RawCandidateSection parent;
                String key = "";

                public void setParent(RawCandidateSection item) {
                    this.parent = item;
                }

                public void startTag(String localname, Map attribites) throws SAXException {
                    if (localname.equals("extra_value")) {
                        this.key = (String) attribites.get("key");
                    }

                }

                public void endTag(String localname, StringBuffer content) throws SAXException {
                    if (localname.equals("extra_value")) {
                        parent.setExtraValue(key, content.toString());
                    }

                }

                public List<String> createXMLLines() {
                    ArrayList<String> ret = new ArrayList<String>();
                    for (String key : parent.getExtraMap().keySet()) {
                        ret.add("<extra_value key='" + key + "'>" + parent.getExtraMap().get(key) + "</extra_value>");
                    }

                    return ret;
                }
            };
        } else {
            if (type.equals("SnrBlock")) {
                return new ComplicatedElementHandler<RawCandidateSection>() {

                    private RawCandidateSection sec;
                    double[] dmIndex = new double[]{0};
                    double[] periodIndex = new double[]{0};
                    double[] accnIndex = new double[]{0};
                    double[] jerkIndex = new double[]{0};
                    double max = 0;
                    double min = 0;
                    String format = "02X";

                    public List<String> createXMLLines() {
                        ArrayList<String> out = new ArrayList<String>();
                        SNRBlock block = sec.getSnrBlock();
			if(block!=null){
				out.add("\t<snr_block>");
				out.add("\t<period_index>" + StringConvertable.DOUBLEARRAY.toString(block.getPeriodIndex()) + "</period_index>");
				out.add("\t<dm_index>" + StringConvertable.DOUBLEARRAY.toString(block.getDmIndex()) + "</dm_index>");
				out.add("\t<accn_index>" + StringConvertable.DOUBLEARRAY.toString(block.getAccnIndex()) + "</accn_index>");
				out.add("\t<jerk_index>" + StringConvertable.DOUBLEARRAY.toString(block.getJerkIndex()) + "</jerk_index>");


				double[][][][] data = block.getBlock();
				double max = block.getMax();
				double min = block.getMin();

				out.add("\t<block_data format='02X' min='" + min + "' max='" + max + "'>");
				int count = 80;
				StringBuffer buf = new StringBuffer();
				//                        Formatter formatter = new Formatter(buf);
				buf.append("\n");
				for (int x = 0; x < block.getDmIndex().length; x++) {
					for (int y = 0; y < block.getPeriodIndex().length; y++) {
						for (int z = 0; z < block.getAccnIndex().length; z++) {
							for (int q = 0; q < block.getJerkIndex().length; q++) {
								int intV = (int) (255.0 *( data[x][y][z][q] - min) / (max - min));
								//                                        formatter.format("%02X", intV);
								buf.append(HexLookup.twochar[intV]);
								count-=2;
								if (count <= 0) {
									count = 80;
									buf.append("\n");
								}
							}
						}
					}
				}
				buf.append("\n");
				out.add(buf.toString());
				out.add("\t</block_data>");
				out.add("</snr_block>");
			}
                        return out;

                    }

                    public void endTag(String localname, StringBuffer content) throws SAXException {
                        if (localname.equals("period_index")) {
                            periodIndex = StringConvertable.DOUBLEARRAY.fromString(content.toString());
                        } else if (localname.equals("dm_index")) {
                            dmIndex = StringConvertable.DOUBLEARRAY.fromString(content.toString());
                        } else if (localname.equals("accn_index")) {
                            accnIndex = StringConvertable.DOUBLEARRAY.fromString(content.toString());
                        } else if (localname.equals("jerk_index")) {
                            jerkIndex = StringConvertable.DOUBLEARRAY.fromString(content.toString());
                        } else if (localname.equals("block_data")) {
                            double[][][][] data = new double[dmIndex.length][periodIndex.length][accnIndex.length][jerkIndex.length];

                            int digitsPerSamp = 2;
                            if (format.endsWith("X")) {
                                digitsPerSamp = Integer.parseInt(format.substring(0, format.length() - 1));
                            } else {
                                throw new RuntimeException("We can only support double arrays encoded as hexadecimal with a 0??X format");
                            }

                            int k = 0;
                            double nmax = Math.pow(16, digitsPerSamp);
                            for (int x = 0; x < dmIndex.length; x++) {
                                for (int y = 0; y < periodIndex.length; y++) {
                                    for (int z = 0; z < accnIndex.length; z++) {
                                        for (int q = 0; q < jerkIndex.length; q++) {

                                            int intV = 0;
                                            int posn = (int) nmax;
                                            for (int n = digitsPerSamp - 1; n >= 0; n--) {
                                                char c = content.charAt(k++);
                                                posn /= 16;
                                                while (!Character.isLetterOrDigit(c)) {
                                                    c = content.charAt(k++);
                                                }
                                                intV += Character.digit(c, 16) * posn;

                                            }
                                            data[x][y][z][q] = ((double) intV)/255.0 * (max - min) + min;
                                        }
                                    }
                                }
                            }



                            SNRBlock block = new SNRBlock(dmIndex, periodIndex, accnIndex, jerkIndex, data);
                            block.setMax(max);
                            block.setMin(min);
                            sec.setSnrBlock(block);
                        }
                    }

                    public void setParent(RawCandidateSection item) {
                        this.sec = item;
                    }

                    public void startTag(String localname, Map<String, String> attribites) throws SAXException {
                        if (localname.equals("block_data")) {
                            min = Double.parseDouble(attribites.get("min"));
                            max = Double.parseDouble(attribites.get("max"));
                        }
                    }
                };
            }
        }
        return null;
    }

    public Map<String, String> getXmlAttributes() {
        HashMap<String, String> attr = new HashMap<String, String>();
        attr.put("name", this.name);
        return attr;
    }

    public List<String> getXmlComplexElements() {
        return xmlComplexElements;
    }

    public void setXmlAttributes(Map<String, String> attr) {
        this.name = attr.get("name");
    }
    protected static HashMap<String, StringConvertable> xmlParameters = new HashMap<String, StringConvertable>();
    protected static ArrayList<String> xmlSubObjects = new ArrayList<String>();
    protected static ArrayList<String> xmlComplexElements = new ArrayList<String>();
    

    static {
        /* 
        private String name;
        private EncodedTwoDimArray encodedSubintegrations = null;
        private EncodedTwoDimArray encodedSubbands = null;
        private EncodedOneDimArray encodedProfile = null;
        private SNRBlock snrBlock;
        private double bestTopoPeriod = -1;
        private double bestBaryPeriod = -1;
        private double bestDm = 0;
        private double bestAccn = 0;
        private double bestJerk = 0;
        private double bestSnr = 0;
        private double bestWidth = -1;
        private double tsamp = -1;
         */

        //  xmlParameters.put("Name", StringConvertable.STRING); NOW an attribute
        xmlParameters.put("EncodedSubintegrations", StringConvertable.HEX_ENCODED_2D_ARRAY);
        xmlParameters.put("EncodedSubbands", StringConvertable.HEX_ENCODED_2D_ARRAY);
        xmlParameters.put("EncodedProfile", StringConvertable.HEX_ENCODED_1D_ARRAY);
        xmlParameters.put("BestTopoPeriod", StringConvertable.FLOAT);
        xmlParameters.put("BestBaryPeriod", StringConvertable.FLOAT);
        xmlParameters.put("BestDm", StringConvertable.FLOAT);
        xmlParameters.put("BestAccn", StringConvertable.FLOAT);
        xmlParameters.put("BestJerk", StringConvertable.FLOAT);
        xmlParameters.put("BestSnr", StringConvertable.FLOAT);
        xmlParameters.put("BestWidth", StringConvertable.FLOAT);
        xmlParameters.put("Tsamp", StringConvertable.FLOAT);

        xmlComplexElements.add("SnrBlock");
        xmlComplexElements.add("ExtraValue");
    }
}
