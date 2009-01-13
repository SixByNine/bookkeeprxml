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
public class Data implements XMLAble {

    private String filename;
    private String dataType;
    private int sequence = 0;
    private TypedString dataUid;
    private TypedString checksum;
    private long headerLength;
    private long blockHeaderLength;
    private long blockSize;
    private int bitsPerSample;
    private String dataOrder;
    private String bitOrderFirstSampleIn;
    private String endian;
    private boolean signed;
    private ArrayList<BlockHeader> blockHeaders = new ArrayList<BlockHeader>();

    public String getBitOrderFirstSampleIn() {
        return bitOrderFirstSampleIn;
    }

    public void setBitOrderFirstSampleIn(String bitOrderFirstSampleIn) {
        this.bitOrderFirstSampleIn = bitOrderFirstSampleIn;
    }

    
    public int getBitsPerSample() {
        return bitsPerSample;
    }

    public void setBitsPerSample(int bitsPerSample) {
        this.bitsPerSample = bitsPerSample;
    }

    public long getBlockHeaderLength() {
        return blockHeaderLength;
    }

    public void setBlockHeaderLength(long blockHeaderLength) {
        this.blockHeaderLength = blockHeaderLength;
    }

    public long getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(long blockSize) {
        this.blockSize = blockSize;
    }

    public TypedString getChecksum() {
        return checksum;
    }

    public void setChecksum(TypedString checksum) {
        this.checksum = checksum;
    }

    public String getDataOrder() {
        return dataOrder;
    }

    public void setDataOrder(String dataOrder) {
        this.dataOrder = dataOrder;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public TypedString getDataUid() {
        return dataUid;
    }

    public void setDataUid(TypedString dataUid) {
        this.dataUid = dataUid;
    }

    public String getEndian() {
        return endian;
    }

    public void setEndian(String endian) {
        this.endian = endian;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(long headerLength) {
        this.headerLength = headerLength;
    }

    public boolean getSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void addBlockHeader(BlockHeader bh) {
        this.blockHeaders.add(bh);
    }

    public List<BlockHeader> getBlockHeaderList() {
        return this.blockHeaders;
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
    protected static HashMap<String, StringConvertable> xmlParameters = new HashMap<String, StringConvertable>();
    protected static ArrayList<String> xmlSubObjects = new ArrayList<String>();
    

    static {
        xmlParameters.put("Filename", StringConvertable.STRING);
        xmlParameters.put("DataType", StringConvertable.STRING);
        xmlParameters.put("Sequence", StringConvertable.INT);
        xmlParameters.put("DataUid", StringConvertable.TYPEDSTRING);
        xmlParameters.put("Checksum", StringConvertable.TYPEDSTRING);
        xmlParameters.put("HeaderLength", StringConvertable.DATA);
        xmlParameters.put("BlockHeaderLength", StringConvertable.DATA);
        xmlParameters.put("BlockSize", StringConvertable.DATA);
        xmlParameters.put("BitsPerSample", StringConvertable.INT);
        xmlParameters.put("DataOrder", StringConvertable.STRING);
        xmlParameters.put("BitOrderFirstSampleIn", StringConvertable.STRING);
        xmlParameters.put("Endian", StringConvertable.STRING);
        xmlParameters.put("Signed", StringConvertable.BOOLEAN);
    }
}
