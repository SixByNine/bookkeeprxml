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

import bookkeepr.DummyIdAble;
import bookkeepr.xml.IdAble;
import bookkeepr.xml.XMLAble;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mike Keith
 */
public abstract class Index<E extends IdAble> implements XMLAble {

    ArrayList<E> list = new ArrayList<E>();

    public void addItem(E obj) {
        this.addItemIsExisting(obj);
    }

    public boolean addItemIsExisting(E obj) {
        if(obj.getId()==0){
            this.list.add(0, obj);
            return false;
        }

        int index = Collections.binarySearch(list, obj, IdAble.COMPARATOR);
        if (index < 0) {
            this.list.add(-index - 1, obj);
            return false;
        } else {
            this.list.remove(index);
            this.list.add(index, obj);
            return true;
        }
    }

    public boolean remove(E obj) {
        int index = Collections.binarySearch(list, obj, IdAble.COMPARATOR);
        if (index < 0) {
            return false;
        } else {
            this.list.remove(index);
            return true;
        }
    }

    public E getItem(long id) {
        IdAble tst = new DummyIdAble(id);
        int index = Collections.binarySearch(list, tst, IdAble.COMPARATOR);
        if (index < 0) {
            return null;
        }
        return this.list.get(index);
    }

    public void setList(ArrayList<E> list) {
        this.list = list;
    }

    public int getSize() {
        return list.size();
    }

    public List<E> getIndex() {
        return list;
    }
}
