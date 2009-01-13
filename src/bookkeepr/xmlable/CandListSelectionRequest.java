/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bookkeepr.xmlable;

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
public class CandListSelectionRequest implements XMLAble {

    private String nameMatch;
    private boolean useRegex = false;
    private Date startCompletedDate;
    private Date endCompletedDate;
    private long[] acceptPsrxmlIds;
    private long[] acceptProcessingIds;

    public long[] getAcceptProcessingIds() {
        return acceptProcessingIds;
    }

    public void setAcceptProcessingIds(long[] acceptProcessingIds) {
        this.acceptProcessingIds = acceptProcessingIds;
    }

    public long[] getAcceptPsrxmlIds() {
        return acceptPsrxmlIds;
    }

    public void setAcceptPsrxmlIds(long[] acceptPsrxmlIds) {
        this.acceptPsrxmlIds = acceptPsrxmlIds;
    }

    public Date getEndCompletedDate() {
        return endCompletedDate;
    }

    public void setEndCompletedDate(Date endCompletedDate) {
        this.endCompletedDate = endCompletedDate;
    }

    public String getNameMatch() {
        return nameMatch;
    }

    public void setNameMatch(String nameMatch) {
        this.nameMatch = nameMatch;
    }

    public Date getStartCompletedDate() {
        return startCompletedDate;
    }

    public void setStartCompletedDate(Date startCompletedDate) {
        this.startCompletedDate = startCompletedDate;
    }

    public boolean getUseRegex() {
        return useRegex;
    }

    public void setUseRegex(boolean useRegex) {
        this.useRegex = useRegex;
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
        xmlParameters.put("MatchName", StringConvertable.STRING);
        xmlParameters.put("UseRegex", StringConvertable.BOOLEAN);
        xmlParameters.put("StartCompletedDate", StringConvertable.ISODATE);
        xmlParameters.put("EndCompletedDate", StringConvertable.ISODATE);
        xmlParameters.put("AcceptPsrxmlIds", StringConvertable.IDARRAY);
        xmlParameters.put("AcceptProcessingIds", StringConvertable.IDARRAY);

    }
}
