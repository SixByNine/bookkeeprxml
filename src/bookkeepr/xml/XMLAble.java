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

package bookkeepr.xml;

import bookkeepr.xml.StringConvertable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kei041
 */
public interface XMLAble {

    /**
     * 
     * Returns the field names of xmlable elements that can be contained in this element
     * 
     * @return
     */
    public List<String> getXmlSubObjects();
    
    /**
     * 
     * Returns the name of the class that this represtents within the bookkeepr.xmlable package
     * 
     * @return
     */
    public String getClassName();
    
    /**
     * Returns a hash map of the fields of this object that are to be converted
     * to XML in the xml read/write process.
     * 
     * @return
     */
    public HashMap<String,StringConvertable> getXmlParameters();
    
}
