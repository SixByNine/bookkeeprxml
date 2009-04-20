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
public class SurveyStatus implements XMLAble {

    private ArrayList<SurveyStatusSection> list = new ArrayList<SurveyStatusSection>();
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
    
    public List<SurveyStatusSection> getSurveyStatusSectionList() {
        return list;
    }

    public void addSurveyStatusSection(SurveyStatusSection obj) {
        list.add(obj);
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
        xmlSubObjects.add("SurveyStatusSection");
        xmlParameters.put("Date", StringConvertable.ISODATE);
    }
}
