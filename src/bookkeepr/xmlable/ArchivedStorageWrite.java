/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bookkeepr.xmlable;

import bookkeepr.xml.IdAble;
import bookkeepr.xml.StringConvertable;
import bookkeepr.xml.XMLAble;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kei041
 */
public class ArchivedStorageWrite implements XMLAble, IdAble {

    private long id;
    private long storageId;
    private long psrxmlId;
    private float writeSize;
    private Date dateWritten;
    private String fileLabel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPsrxmlId() {
        return psrxmlId;
    }

    public void setPsrxmlId(long psrxmlId) {
        this.psrxmlId = psrxmlId;
    }

    public long getStorageId() {
        return storageId;
    }

    public void setStorageId(long storageId) {
        this.storageId = storageId;
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    public Date getDateWritten() {
        return dateWritten;
    }

    public void setDateWritten(Date dateWritten) {
        this.dateWritten = dateWritten;
    }

    public String getFileLabel() {
        return fileLabel;
    }

    public void setFileLabel(String fileLabel) {
        this.fileLabel = fileLabel;
    }

    public float getWriteSize() {
        return writeSize;
    }

    public void setWriteSize(float writeSize) {
        this.writeSize = writeSize;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ArchivedStorageWrite other = (ArchivedStorageWrite) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.storageId != other.storageId) {
            return false;
        }
        if (this.psrxmlId != other.psrxmlId) {
            return false;
        }
        if (Math.abs(this.writeSize-other.writeSize) > 0.01) {
            return false;
        }
        if (this.dateWritten != other.dateWritten && (this.dateWritten == null || !this.dateWritten.equals(other.dateWritten))) {
            return false;
        }
        if (this.fileLabel != other.fileLabel && (this.fileLabel == null || !this.fileLabel.equals(other.fileLabel))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 71 * hash + (int) (this.storageId ^ (this.storageId >>> 32));
        hash = 71 * hash + (int) (this.psrxmlId ^ (this.psrxmlId >>> 32));
        hash = 71 * hash + Float.floatToIntBits(this.writeSize);
        hash = 71 * hash + (this.dateWritten != null ? this.dateWritten.hashCode() : 0);
        hash = 71 * hash + (this.fileLabel != null ? this.fileLabel.hashCode() : 0);
        return hash;
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
        xmlParameters.put("StorageId", StringConvertable.ID);
        xmlParameters.put("PsrxmlId", StringConvertable.ID);
        xmlParameters.put("DateWritten", StringConvertable.ISODATE);
        xmlParameters.put("FileLabel", StringConvertable.STRING);
        xmlParameters.put("WriteSize", StringConvertable.FLOAT);
    }
}
