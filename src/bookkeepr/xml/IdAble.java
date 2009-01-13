/*
 * Copyright (C) 2005-2008 Michael Keith, CSIRO
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

import bookkeepr.xml.XMLAble;
import java.util.Comparator;

/**
 *
 * @author Mike Keith
 */
public interface IdAble extends XMLAble {

    public long getId();
    public void setId(long id);
    
    
    
    public static Comparator<IdAble> COMPARATOR = new Comparator<IdAble>(){

        public int compare(IdAble o1, IdAble o2) {
            long v = o1.getId() - o2.getId();
            if ( v > 0)return 1;
            if ( v < 0)return -1;
            return 0;
        }
        
        
    };
}
