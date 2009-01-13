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
 * PlotTwoDim.java
 *
 * Created on 29 January 2007, 10:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pulsarhunter.displaypanels;

import pulsarhunter.jreaper.Colourmap;

/**
 *
 * @author mkeith
 */
public class PlotTwoDim extends MKPlot2D{
    
    private boolean clicked = false;
    
    public PlotTwoDim(String title, String xLabel, String yLabel,  double[][] zVals,Colourmap colourmap) {
        super(colourmap);
        double[] xVals  = new double[zVals.length];
        double[] yVals  = new double[zVals[0].length];
        for(int i = 0; i < xVals.length; i++)xVals[i] = i;
        for(int i = 0; i < yVals.length; i++)yVals[i] = i;
        init(xLabel,yLabel,title,xVals,yVals,zVals);
    }
    
    
    /** Creates a new instance of PlotTwoDim */
    public PlotTwoDim(String title,String xLabel, String yLabel, double[] xVals, double[] yVals, double[][] zVals,Colourmap colourmap) {
        super(colourmap);
        init(xLabel,yLabel,title,xVals,yVals,zVals);
        
    }
    
    private void init(String xLabel, String yLabel, String title,double[] xVals, double[] yVals, double[][] zVals){
        this.setTitle(title);
        this.setXLabel(xLabel);
        this.setYLabel(yLabel);
        this.setValues(xVals,yVals,zVals);
    }
    
    
    
}
