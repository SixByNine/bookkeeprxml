package bookkeepr.xmlable;

import bookkeepr.xml.StringConvertable;
import bookkeepr.xml.XMLAble;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mike Keith
 */
public class Log implements XMLAble {

    private ArrayList<LogItem> list = new ArrayList<LogItem>();
    private String currentTime;

    public List<LogItem> getLogItemList() {
        return list;
    }

    public void addLogItem(LogItem obj) {
        list.add(obj);
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
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
        xmlSubObjects.add("LogItem");
        xmlParameters.put("CurrentTime", StringConvertable.STRING);
    }
}
