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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kei041
 */
public class BlockHeader {

    private int blockNuber;
    private String blockSha1;

    public int getBlockNuber() {
        return blockNuber;
    }

    public void setBlockNuber(int blockNuber) {
        this.blockNuber = blockNuber;
    }

    public String getBlockSha1() {
        return blockSha1;
    }

    public void setBlockSha1(String blockSha1) {
        this.blockSha1 = blockSha1;
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
        xmlParameters.put("BlockNumber", StringConvertable.INT);
        xmlParameters.put("BlockSha1", StringConvertable.STRING);
    }
}
