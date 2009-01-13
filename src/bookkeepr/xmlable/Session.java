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
public class Session implements IdAble, XMLAble {

    private long id = 0;
    private ArrayList<Long> modifiedKey = new ArrayList<Long>();
    private ArrayList<IdAble> modified = null;
    private ArrayList<IdAble> externalItems = null;

    public ArrayList<Long> getModifiedKeyList() {
        return modifiedKey;
    }

    public void addModifiedKey(long key) {
        int index = Collections.binarySearch(modifiedKey, key);
        if (index < 0) {
            this.modifiedKey.add(-index - 1, key);
        } else {
            this.modifiedKey.remove(index);
            this.modifiedKey.add(index, key);
        }
    }

    public ArrayList<Long> getModifiedKey() {
        return modifiedKey;
    }

    public void setModifiedKey(ArrayList<Long> modifiedKey) {
        this.modifiedKey = modifiedKey;
    }

    public void addModifiedItem(IdAble idable) {
        if (!this.modifiedKey.contains(idable.getId())) {
            this.modified.add(idable);
            this.modifiedKey.add(idable.getId());
        }
    }

    public ArrayList<IdAble> getExternalItems() {
        return externalItems;
    }

    public void addExternalItem(IdAble idable) {
        this.externalItems.add(idable);
    }

    public ArrayList<IdAble> getModified() {
        return modified;
    }

    public boolean getClosed() {
        return modified == null;
    }

    public void setClosed(boolean closed) {
        if (closed) {
            modified = null;
        } else {
            modifiedKey = new ArrayList<Long>();
            modified = new ArrayList<IdAble>();
            externalItems = new ArrayList<IdAble>();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        xmlParameters.put("ModifiedKey", StringConvertable.IDLIST);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IdAble) {
            return ((IdAble) obj).getId() == this.id;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new Long(this.id).hashCode();
    }
}
