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
 * MKPlot2D.java
 *
 * Created on 29 January 2007, 10:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package pulsarhunter.displaypanels;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import pulsarhunter.jreaper.Colourmap;

/**
 *
 * @author mkeith
 */
public class MKPlot2D extends MKPlot {

    public enum TwoDPlotType {

        FILLED_AREA
    };
    private double[][] zVals;
    private double[][] zVals_norm = null;
    private boolean normalised;
    private double zMin,  zMax;
    private TwoDPlotType plotType = TwoDPlotType.FILLED_AREA;
    private Colourmap colourmap;

    /** Creates a new instance of MKPlot2D */
    public MKPlot2D(Colourmap colourmap) {
        super(Color.RED, Color.GREEN);
        this.colourmap = colourmap;
    }

    public void setValues(double[] xVals, double[] yVals, double[][] zVals) {


        // Create a 'Virtual' Axis, with one extra data point
        // This is so that the grey plots have enough extra room for the last point

        double[] xVals2 = new double[xVals.length + 1];
        double[] yVals2 = new double[yVals.length + 1];

        System.arraycopy(xVals, 0, xVals2, 0, xVals.length);
        xVals2[xVals.length] = xVals[xVals.length - 1] + (xVals[xVals.length - 1] - xVals[xVals.length - 2]);

        System.arraycopy(yVals, 0, yVals2, 0, yVals.length);

        yVals2[yVals.length] = yVals[yVals.length - 1] + (yVals[yVals.length - 1] - yVals[yVals.length - 2]);


        this.setVals(xVals2, yVals2);

        this.zVals = zVals;

        double max = -Double.MAX_VALUE;
        double min = Double.MAX_VALUE;
        for (double[] zValsArr : zVals) {
            for (double j : zValsArr) {
                if (j > max) {
                    max = j;
                }
                if (j < min) {
                    min = j;
                }
            }
        }
        this.setZMax(max);
        this.setZMin(min);
    }

    public double getZMin() {
        return zMin;
    }

    public void setZMin(double zMin) {
        this.zMin = zMin;
    }

    public double getZMax() {
        return zMax;
    }

    public void setZMax(double zMax) {
        this.zMax = zMax;
    }

    public void setNormalised(boolean normalised) {
        this.normalised = normalised;

        if (this.zVals_norm == null) {
            zVals_norm = new double[zVals.length][zVals[0].length];

            for (int j = 0; j < zVals[0].length; j++) {

                double min = Double.MAX_VALUE;
                double max = -Double.MIN_VALUE;
                for (int i = 0; i < zVals.length; i++) {


                    if (zVals[i][j] > max) {
                        max = zVals[i][j];
                    }
                    if (zVals[i][j] < min) {
                        min = zVals[i][j];
                    }
                }

                for (int i = 0; i < zVals.length; i++) {
                    zVals_norm[i][j] = (zVals[i][j] - min) / (max - min);
                }
            }
        }
        this.repaint();
    }

    public boolean isNormalised() {
        return normalised;
    }

    protected void drawMargins(Graphics2D g2) {
        /**
         * Draw the borders...
         */
        double xmin = this.getXmin();
        double xmax = this.getXmax();
        double ymin = this.getYmin();
        double ymax = this.getYmax();
        int[] margin = this.getMargin();

        double[] xVals = this.getXVals();
        double[] yVals = this.getYVals();

        g2.setFont(new java.awt.Font("Monospaced", 0, 9));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        // top and bottom
        g2.drawLine(getXScreenPosn(xmin), getYScreenPosn(ymin), getXScreenPosn(xmax), getYScreenPosn(ymin));
        g2.drawLine(getXScreenPosn(xmax), getYScreenPosn(ymax), getXScreenPosn(xmin), getYScreenPosn(ymax));
        // left and right
        g2.drawLine(getXScreenPosn(xmin), getYScreenPosn(ymin), getXScreenPosn(xmin), getYScreenPosn(ymax));
        g2.drawLine(getXScreenPosn(xmax), getYScreenPosn(ymin), getXScreenPosn(xmax), getYScreenPosn(ymax));

        /**
         * Draw the Scale...
         */
//        
//        double nticks = (this.getWidth()-(margin[0] + margin[1]))/50.0;
//        int xstep = (int)(xVals.length/nticks);
//        if( xstep == 0 ) xstep = 1;
//        for(int i = 0; i < xVals.length; i+= xstep){
//            double x = xVals[i];
//            g2.drawString(shortStringOf(x),getXScreenPosn(x)-8,getYScreenPosn(ymin)+20);
//            
//            // ticks
//            g2.drawLine(getXScreenPosn(x),getYScreenPosn(ymin),getXScreenPosn(x),getYScreenPosn(ymin)+5);
//            
//            // small ticks
//            g2.drawLine(getXScreenPosn(x+xstep/2.0),getYScreenPosn(ymin),getXScreenPosn(x+xstep/2.0),getYScreenPosn(ymin)+2);
//        }
//        nticks = (this.getHeight()-(margin[0] + margin[1]))/50.0;
//        int ystep = (int)(yVals.length/nticks);
//        if( ystep == 0 ) ystep = 1;
//        for(int i = 0; i < yVals.length; i+= ystep){
//            double y = yVals[i];
//            // g2.drawString(shortStringOf(y),getXScreenPosn(xmin)-35,getYScreenPosn(y));
//            
//            g2.rotate(Math.toRadians(90),getXScreenPosn(xmin)-20,getYScreenPosn(y)-8);
//            
//            g2.drawString(shortStringOf(y),getXScreenPosn(xmin)-20,getYScreenPosn(y)-8);
//            g2.rotate(Math.toRadians(-90),getXScreenPosn(xmin)-20,getYScreenPosn(y)-8);
//            
//            // ticks
//            g2.drawLine(getXScreenPosn(xmin),getYScreenPosn(y),getXScreenPosn(xmin)-5,getYScreenPosn(y));
//            
//            // small ticks
//            g2.drawLine(getXScreenPosn(xmin),getYScreenPosn(y+ystep/2.0),getXScreenPosn(xmin)-2,getYScreenPosn(y+ystep/2.0));
//        }
//        
//        
//        g2.drawString(this.getXLabel(),getXScreenPosn((xmax-xmin)/2.0 + xmin) - this.getXLabel().length()*2,getYScreenPosn(ymin)+35);
//        
//        
//        g2.rotate(Math.toRadians(90),getXScreenPosn(xmin)-35,getYScreenPosn((ymax-ymin)/2.0 + ymin)- this.getYLabel().length()*2);
//        g2.drawString(this.getYLabel(),getXScreenPosn(xmin)-35,getYScreenPosn((ymax-ymin)/2.0 + ymin)- this.getYLabel().length()*2);
//        g2.rotate(Math.toRadians(-90),getXScreenPosn(xmin)-35,getYScreenPosn((ymax-ymin)/2.0 + ymin)- this.getYLabel().length()*2);
        this.drawScale(g2);



        g2.setFont(new java.awt.Font("Monospaced", Font.BOLD, 12));
        g2.drawString(this.getTitle(), getXScreenPosn((xmax - xmin) / 2.0 + xmin) - this.getTitle().length() * 3, getYScreenPosn(ymax) - 10);
    }

    protected void drawGraph(Graphics2D g2) {
        switch (plotType) {
            case FILLED_AREA:
                drawFilledAreaPlot(g2);
                break;
        }
    }

    private void drawFilledAreaPlot(Graphics2D g2) {

        double[] xVals = this.getXVals();
        double[] yVals = this.getYVals();


        for (int i = 0; i < xVals.length - 1; i++) {
            for (int j = 0; j < yVals.length - 1; j++) {
                int grey;
                if (this.normalised) {
                    grey = (int) (zVals_norm[i][j] * 255);
                } else {
                    grey = (int) (((zVals[i][j] - zMin) / (zMax - zMin)) * 255);
                }
                int r = colourmap.getGCols()[0][grey];
                int g = colourmap.getGCols()[1][grey];
                int b = colourmap.getGCols()[2][grey];
                g2.setColor(new Color(r, g, b));
                int blockHeight = this.getYScreenPosn(yVals[j]) - this.getYScreenPosn(yVals[j + 1]);
                int blockWidth = this.getXScreenPosn(xVals[i + 1]) - this.getXScreenPosn(xVals[i]);
                g2.fillRect(this.getXScreenPosn(xVals[i]), this.getYScreenPosn(yVals[j]) - blockHeight, blockWidth, blockHeight);
            //System.out.println(grey+" "+this.getYScreenPosn(yVals[j-1])+" "+this.getYScreenPosn(yVals[j]));

            }
        }

    }

    private void drawSizedOvalPlot(Graphics2D g2) {

        double[] xVals = this.getXVals();
        double[] yVals = this.getYVals();

        for (int i = 0; i < xVals.length - 1; i++) {
            for (int j = 0; j < yVals.length - 1; j++) {


                int grey = (int) (((zVals[i][j] - zMin) / (zMax - zMin)) * 255);
                //g2.setColor(new Color(255-grey,255-grey,255-grey));
                g2.setColor(new Color(0, 0, 0, grey));
                g2.setComposite(AlphaComposite.SrcOver);
                int blockHeight = grey / 35;
                int blockWidth = grey / 35;
//                g2.fillRect(this.getXScreenPosn(xVals[i]),this.getYScreenPosn(yVals[j])-blockHeight,blockWidth,blockHeight);

                g2.fillOval(this.getXScreenPosn(xVals[i]), this.getYScreenPosn(yVals[j]), blockWidth, blockHeight);

            //System.out.println(grey+" "+this.getYScreenPosn(yVals[j-1])+" "+this.getYScreenPosn(yVals[j]));

            }
        }

    }
}

