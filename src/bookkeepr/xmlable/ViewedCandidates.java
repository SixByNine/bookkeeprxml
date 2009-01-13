/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bookkeepr.xmlable;

import bookkeepr.xml.IdAble;
import bookkeepr.xml.StringConvertable;
import bookkeepr.xml.XMLAble;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kei041
 */
public class ViewedCandidates implements XMLAble, IdAble {

    private long id;
    private String name;
    private ArrayList<Long> viewed = new ArrayList<Long>();

    public void append(ViewedCandidates extended) {
        for (Long l : extended.getViewed()) {
            this.addViewed(l);
        }
    }

    public void addViewed(Long id) {
        int index = Collections.binarySearch(viewed, id);
        // only add unique items.
        if (index < 0) {
            this.viewed.add(-index - 1, id);
        }
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

    public ArrayList<Long> getViewed() {
        return viewed;
    }

    public void setViewed(ArrayList<Long> viewed) {
        this.viewed = viewed;
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
        xmlParameters.put("Viewed", StringConvertable.IDLIST);

    }
}
