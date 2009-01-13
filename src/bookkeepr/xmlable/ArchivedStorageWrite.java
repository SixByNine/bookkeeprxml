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

    }
}
