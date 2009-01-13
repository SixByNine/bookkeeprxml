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
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author kei041
 */
public class Configuration implements XMLAble, IdAble {

    private long id = 0;
    String name;
    private String configString;
    private float tobs;
    private long telescopeId;
    private long receiverId;
    private boolean galacticOrientated = true;

    public String getConfigString() {
        return configString;
    }

    public void setConfigString(String configString) {
        this.configString = configString;
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

    public float getTobs() {
        return tobs;
    }

    public void setTobs(float tobs) {
        this.tobs = tobs;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public long getTelescopeId() {
        return telescopeId;
    }

    public void setTelescopeId(long telescopeId) {
        this.telescopeId = telescopeId;
    }

    public boolean getGalacticOrientated() {
        return galacticOrientated;
    }

    public void setGalacticOrientated(boolean galacticOrientated) {
        this.galacticOrientated = galacticOrientated;
    }

    public String getScheduleLine(Pointing target) {
        String str = this.getConfigString();
        StringBuffer glS = new StringBuffer();
        StringBuffer gbS = new StringBuffer();
        
        Formatter form = new Formatter(glS);
        double gl = target.getCoordinate().getGl();
        if (gl < 0)gl+=360;
        form.format("%4.3f",gl);
        form = new Formatter(gbS);
        double gb = target.getCoordinate().getGb();
        form.format("%4.3f",gb);
        
        
        str = Pattern.compile("\\{RA\\}").matcher(str).replaceAll(target.getCoordinate().getRA().toString(false));
        str = Pattern.compile("\\{DEC\\}").matcher(str).replaceAll(target.getCoordinate().getDec().toString(false));
        str = Pattern.compile("\\{GID\\}").matcher(str).replaceAll(target.getGridId());
        str = Pattern.compile("\\{GL\\}").matcher(str).replaceAll(glS.toString());
        str = Pattern.compile("\\{GB\\}").matcher(str).replaceAll(gbS.toString());
        str = Pattern.compile("\\{TOBS\\}").matcher(str).replaceAll(String.valueOf(this.getTobs()));

        return str;
    }

    @Override
    public String toString() {
        return this.getName();
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
        xmlParameters.put("ConfigString", StringConvertable.STRING);
        xmlParameters.put("Tobs", StringConvertable.FLOAT);
        xmlParameters.put("TelescopeId", StringConvertable.ID);
        xmlParameters.put("ReceiverId", StringConvertable.ID);
        xmlParameters.put("GalacticOrientated", StringConvertable.BOOLEAN);

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
