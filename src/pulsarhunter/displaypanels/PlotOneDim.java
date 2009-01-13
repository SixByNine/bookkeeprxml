/*
Copyright (C) 2005-2007 Michael Keith, University Of Manchester

email: mkeith@pulsarastronomy.net
www  : www.pulsarastronomy.net/wiki/Software/PulsarHunter

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

*/
/*
 * PlotOneDim.java
 *
 * Created on 25 January 2007, 16:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pulsarhunter.displaypanels;

import java.awt.Color;

/**
 *
 * @author mkeith
 */
public class PlotOneDim extends MKPlot{
    
    public PlotOneDim(String title, String xAxisName, String yAxisName, double[] xvals, double[] yvals) {
        this(title,xAxisName,yAxisName,xvals,yvals,new double[0],new double[0],Color.RED,Color.GREEN);
    }
    
    public PlotOneDim(String title, String xAxisName, String yAxisName, double[] xvals, double[] yvals, double[] xdots, double[] ydots, Color c1, Color c2) {
        super(c1,c2);
        
        this.setTitle(title);
        this.setXLabel(xAxisName);
        this.setYLabel(yAxisName);
        this.setVals(xvals,yvals);
        
        this.setPoints(xdots,ydots);
        
    }
    
}
