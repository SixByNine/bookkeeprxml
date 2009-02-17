/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bookkeepr.xmlable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kei041
 */
public class ArchivedStorageWriteExtended extends ArchivedStorageWrite {

    private Psrxml psrxml;
    private ArchivedStorage archivedStorage;
    //

    public void addPsrxml(Psrxml obj) {
        this.psrxml = obj;
    }

    public List<Psrxml> getPsrxmlList() {
        return Collections.singletonList(psrxml);
    }

    public void addArchivedStorage(ArchivedStorage obj) {
        this.archivedStorage = obj;
    }

    public List<ArchivedStorage> getArchivedStorageList() {
        return Collections.singletonList(archivedStorage);
    }
    private static ArrayList<String> xmlSubObjects = new ArrayList<String>();

    public List<String> getXmlSubObjects() {
        return xmlSubObjects;
    }
    

    static {
        xmlSubObjects.add("Psrxml");
        xmlSubObjects.add("ArchivedStorage");
    }
}
