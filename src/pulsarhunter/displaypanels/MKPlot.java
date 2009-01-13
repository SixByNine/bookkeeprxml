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
 * MKPlot.java
 *
 * Created on 26 January 2007, 16:43
 */

package pulsarhunter.displaypanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Formatter;

/**
 *
 * @author  mkeith
 */
public class MKPlot extends javax.swing.JPanel{
    public enum LineStyle {Histogram,JoinTheDots};
    
    private double xmin = Double.MAX_VALUE, xmax = -Double.MAX_VALUE, ymin = Double.MAX_VALUE, ymax = -Double.MAX_VALUE;
    
    private String xLabel="X",yLabel="Y",title="";
    private double[] xVals,yVals;
    
    private double[] xPoints,yPoints;
    
    private int npoints = 0;
    private int nvals = 0;
    private int[] margin = new int[]{40,5,40,20}; //l r b t
    
    private boolean joinDots = false;
    private boolean image = false;
    private boolean forceImage = false;
    private int imgHeight;
    private int imgWidth;
    private int imgXOffset;
    private int imgYOffset;
    
    private LineStyle linestyle = LineStyle.Histogram;
    
    private ArrayList<ClickNotifyable> notifyList = new ArrayList<ClickNotifyable> ();
    
    private Color colour1;
    private Color colour2;
    
    /** Creates new form MKPlot */
    public MKPlot(Color colour1, Color colour2) {
        this.colour1 = colour1;
        this.colour2 = colour2;
        initComponents();
    }
    
    
    public void paintImage(Graphics2D g,  int width,int height){
        this.paintImage(g,height,width,0,0);
    }
    
    public void paintImage(Graphics2D g,  int height,int width,int xorigin, int yorigin){
        this.image = true;
        this.imgHeight = height;
        this.imgWidth = width;
        this.imgXOffset = xorigin;
        this.imgYOffset = yorigin;
        drawGraph(g);
        drawMargins(g);
        this.image = this.forceImage;
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2 = (Graphics2D)g;
        drawGraph(g2);
        drawMargins(g2);
    }
    
    protected void drawGraph(Graphics2D g2){
        
        
        /**
         * Draw the data
         */
        g2.setPaintMode();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int oldX = -1;
        int oldY = -1;
        for(int i = 0; i < npoints; i++){
            g2.setColor(colour1);
            g2.drawOval(getXScreenPosn(getXPoints()[i])-1,getYScreenPosn(getYPoints()[i])-1,3,3);
            if(isJoinDots()){
                if(getYPoints()[i]!=0){
                    g2.setColor(g2.getColor().darker());
                    if(oldX > 0){
                        g2.drawLine(oldX,oldY, getXScreenPosn(getXPoints()[i]),getYScreenPosn(getYPoints()[i]));
                    }
                    oldX = getXScreenPosn(getXPoints()[i]);
                    oldY = getYScreenPosn(getYPoints()[i]);
                }
            }
        }
        
        if(xVals != null){
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_DEFAULT);
            g2.setColor(colour2);
            if(getXVals().length > 0){
                oldX =  getXScreenPosn(getXVals()[0]);
                oldY = getYScreenPosn(getYVals()[0]);
                for(int i = 1; i < nvals; i++){
                    switch(linestyle){
                        case Histogram:
                            g2.drawLine(getXScreenPosn(getXVals()[i-1]),getYScreenPosn(getYVals()[i]),getXScreenPosn(getXVals()[i]),getYScreenPosn(getYVals()[i]));
                            g2.drawLine(getXScreenPosn(getXVals()[i-1]),getYScreenPosn(getYVals()[i]),getXScreenPosn(getXVals()[i-1]),getYScreenPosn(getYVals()[i-1]));
                            break;
                        case JoinTheDots:
                            if(oldX > 0){
                                g2.drawLine(oldX,oldY, getXScreenPosn(getXVals()[i]),getYScreenPosn(getYVals()[i]));
                            }
                            oldX = getXScreenPosn(getXVals()[i]);
                            oldY = getYScreenPosn(getYVals()[i]);
                            
                            break;
                    }
                }
            }
        }
    }
    
    protected void drawMargins(Graphics2D g2){
        /**
         * Draw the borders...
         */
        g2.setFont(new java.awt.Font("Monospaced", 0, 9));
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON); 

        g2.setColor(Color.BLACK);
        // top and bottom
        g2.drawLine(getXScreenPosn(xmin),getYScreenPosn(ymin),getXScreenPosn(xmax),getYScreenPosn(ymin));
        g2.drawLine(getXScreenPosn(xmax),getYScreenPosn(ymax),getXScreenPosn(xmin),getYScreenPosn(ymax));
        // left and right
        g2.drawLine(getXScreenPosn(xmin),getYScreenPosn(ymin),getXScreenPosn(xmin),getYScreenPosn(ymax));
        g2.drawLine(getXScreenPosn(xmax),getYScreenPosn(ymin),getXScreenPosn(xmax),getYScreenPosn(ymax));
        
        
        drawScale(g2);
        
//        /**
//         * Draw the Scale...
//         */
//        double nticks = (this.getWidth()-(margin[0]+margin[1]))/50.0;
//        double xstep = (xmax - xmin)/nticks;
//        if( xstep == 0 ) xstep = 1.0;
//
//        for(double x = xmin; x <= xmax; x+=xstep){
//            String xStr = shortStringOf(x);
//            g2.drawString(xStr,getXScreenPosn(x)-xStr.length()*2,getYScreenPosn(ymin)+20);
//
//            // ticks
//            g2.drawLine(getXScreenPosn(x),getYScreenPosn(ymin),getXScreenPosn(x),getYScreenPosn(ymin)+5);
//
//            // small ticks
//            g2.drawLine(getXScreenPosn(x+xstep/2.0),getYScreenPosn(ymin),getXScreenPosn(x+xstep/2.0),getYScreenPosn(ymin)+2);
//        }
//
//
//
//
//
//        nticks = (this.getHeight()-(margin[2]+margin[3]))/50.0;
//        double ystep = (ymax - ymin)/nticks;
//        if( ystep == 0 ) ystep = 1.0;
//        for(double y = ymin; y <= ymax; y+=ystep){
//            // g2.drawString(shortStringOf(y),getXScreenPosn(xmin)-35,getYScreenPosn(y));
//            String yStr = shortStringOf(y);
//            g2.rotate(Math.toRadians(90),getXScreenPosn(xmin)-20,getYScreenPosn(y)- yStr.length()*2);
//
//            g2.drawString(yStr,getXScreenPosn(xmin)-20,getYScreenPosn(y)- yStr.length()*2);
//            g2.rotate(Math.toRadians(-90),getXScreenPosn(xmin)-20,getYScreenPosn(y)- yStr.length()*2);
//
//            // ticks
//            g2.drawLine(getXScreenPosn(xmin),getYScreenPosn(y),getXScreenPosn(xmin)-5,getYScreenPosn(y));
//
//            // small ticks
//            g2.drawLine(getXScreenPosn(xmin),getYScreenPosn(y+ystep/2.0),getXScreenPosn(xmin)-2,getYScreenPosn(y+ystep/2.0));
//        }
//
//
//        g2.drawString(xLabel,getXScreenPosn((xmax-xmin)/2.0 + xmin)- xLabel.length()*2,getYScreenPosn(ymin)+35);
//
//
//        g2.rotate(Math.toRadians(90),getXScreenPosn(xmin)-35,getYScreenPosn((ymax-ymin)/2.0 + ymin)- yLabel.length()*2);
//        g2.drawString(yLabel,getXScreenPosn(xmin)-35,getYScreenPosn((ymax-ymin)/2.0 + ymin)- yLabel.length()*2);
//        g2.rotate(Math.toRadians(-90),getXScreenPosn(xmin)-35,getYScreenPosn((ymax-ymin)/2.0 + ymin)- yLabel.length()*2);
//
        
        
        /**
         * Draw the title
         *
         */
        g2.setFont(new java.awt.Font("Monospaced", Font.BOLD, 12));
        g2.drawString(title,getXScreenPosn((xmax-xmin)/2.0 + xmin) - title.length()*3,getYScreenPosn(ymax)-10);
    }
    
    protected void drawScale(Graphics2D g2){
        // we need to work out the best scale to draw...
        
        
        
        int maxNticks;
        
        
        double yrange = ymax - ymin;
        if(yrange==0)yrange=1;
        
        maxNticks= (int)((this.getHeight()-(margin[2]+margin[3]))/50.0);
        
        if(maxNticks < 3)maxNticks = 3;
        
        double ystep = yrange / (double)maxNticks;
        
        double yexp = (int)Math.log10(ystep);
        if(ystep < 1)yexp--;
        
        double yexpMult = Math.pow(10,yexp);
        
        
        
        ystep = (double)((int)(ystep/yexpMult + 0.5))*yexpMult;
        
        int nmarks = (int)(yrange/ystep)+1;
        
        if(nmarks > 25){
            ystep = yrange/25;
            nmarks=25;
        }
        
        int nsmallticks = 10;
        if(nmarks < 10)nsmallticks = 5;
        //if(nmarks < 4)nsmallticks = nmarks;
        
        double ysmallstep = ystep / nsmallticks;
        
        
        double y = ((int)(ymin/ystep))*ystep;
        if(y < this.getYmin())y+=ystep;
        double ysmall = y;
        
        // draw the points to the left of the first main tick
        while(ysmall > ymin){
            g2.drawLine(getXScreenPosn(xmin),getYScreenPosn(ysmall),getXScreenPosn(xmin)-2,getYScreenPosn(ysmall));
            ysmall-=ysmallstep;
        }
        
        ysmall = y;
        for(int i = 0; i < nmarks; i++){
            
            String yStr = shortStringOf(y);
            
            g2.rotate(Math.toRadians(90),getXScreenPosn(xmin)-20,getYScreenPosn(y)- yStr.length()*2);
            
            g2.drawString(yStr,getXScreenPosn(xmin)-20,getYScreenPosn(y)- yStr.length()*2);
            g2.rotate(Math.toRadians(-90),getXScreenPosn(xmin)-20,getYScreenPosn(y)- yStr.length()*2);
            
            // ticks
            g2.drawLine(getXScreenPosn(xmin),getYScreenPosn(y),getXScreenPosn(xmin)-5,getYScreenPosn(y));
            
            
            y+=ystep;
            
            while(ysmall < y && ysmall < ymax){
                // small ticks
                g2.drawLine(getXScreenPosn(xmin),getYScreenPosn(ysmall),getXScreenPosn(xmin)-2,getYScreenPosn(ysmall));
                
                ysmall+=ysmallstep;
            }
            if(y > ymax)break;
        }
        
        
        
        double xrange = xmax - xmin;
        if(xrange==0)xrange=1;
        
        maxNticks= (int)((this.getWidth()-(margin[0]+margin[1]))/50.0);
        if(maxNticks < 3)maxNticks = 3;
        double xstep = xrange /(double) maxNticks;
        
        double xexp = (int)Math.log10(xstep);
        if(xstep < 1)xexp--;
        
        double xexpMult = Math.pow(10,xexp);
        
        
        
        xstep = (double)((int)(xstep/xexpMult + 0.5))*xexpMult;
        
        nmarks = (int)(xrange/xstep)+1;
        if(nmarks > 25){
            xstep = xrange/25.0;
            nmarks=25;
        }
        
        nsmallticks = 10;
        if(nmarks < 10)nsmallticks = 5;
        //if(nmarks < 5)nsmallticks = nmarks;
        
        double xsmallstep = xstep / nsmallticks;
        
        
        double x = ((int)(xmin/xstep))*xstep;
        double xsmall = x;
        
        // draw the points to the left of the first main tick
        while(xsmall > xmin){
            g2.drawLine(getXScreenPosn(xsmall),getYScreenPosn(ymin),getXScreenPosn(xsmall),getYScreenPosn(ymin)+2);
            xsmall-=xsmallstep;
        }
        
        xsmall = x;
        for(int i = 0; i < nmarks; i++){
            
            String xStr = shortStringOf(x);
            
            g2.drawString(xStr,getXScreenPosn(x)-xStr.length()*2,getYScreenPosn(ymin)+20);
            
            // ticks
            g2.drawLine(getXScreenPosn(x),getYScreenPosn(ymin),getXScreenPosn(x),getYScreenPosn(ymin)+5);
            x+=xstep;
            
            while(xsmall < x && xsmall < xmax){
                g2.drawLine(getXScreenPosn(xsmall),getYScreenPosn(ymin),getXScreenPosn(xsmall),getYScreenPosn(ymin)+2);
                xsmall+=xsmallstep;
            }
            if(x > xmax)break;
        }
        
        
        
        
        
        
        
        g2.drawString(xLabel,getXScreenPosn((xmax-xmin)/2.0 + xmin)- xLabel.length()*2,getYScreenPosn(ymin)+35);
        
        
        g2.rotate(Math.toRadians(90),getXScreenPosn(xmin)-35,getYScreenPosn((ymax-ymin)/2.0 + ymin)- yLabel.length()*2);
        g2.drawString(yLabel,getXScreenPosn(xmin)-35,getYScreenPosn((ymax-ymin)/2.0 + ymin)- yLabel.length()*2);
        g2.rotate(Math.toRadians(-90),getXScreenPosn(xmin)-35,getYScreenPosn((ymax-ymin)/2.0 + ymin)- yLabel.length()*2);
        
    }
    
    
    protected String shortStringOf(double d){
        
        StringBuilder build = new StringBuilder();
        Formatter formater = new Formatter(build);
        
        
        
        if(Math.abs(d) < 1e-10){
            build.append("0");
        }else if((Math.abs(d) > 1000 && Math.abs((int)d - d) > 0.01) || Math.abs(d) > 10000){
            formater.format("%2.1e",d);
        } else if(Math.abs(d) < 0.01){
            formater.format("%2.1e",d);
        } else {
            if(Math.abs((int)d - d) < 0.01){
                formater.format("%d",(int)d);
            }else{
                formater.format("%4.2f",d);
            }
        }
        
        return build.toString();
    }
    
    
    
    public int getXScreenPosn(double x){
        
        int width = this.getWidth() - (margin[0] + margin[1]); // l and r margins
        //int height = this.getHeight() - 2*margin;
        
        return (int)(( (x - getXmin())/(getXmax()-getXmin()) )*width) + (margin[0]) + this.getXOffset();
        
        
    }
    
    public double getXPlotPosn(int x){
        
        int width = this.getWidth() - (margin[0] + margin[1]);
        //int height = this.getHeight() - 2*margin;
        
        return (getXmax()-getXmin())*(x-(margin[0]) - this.getXOffset())/(double)width + getXmin();
        
        
    }
    
    public int getYScreenPosn(double y){
        
        //int width = this.getWidth() - 2*margin;
        int height = this.getHeight() - (margin[2] + margin[3]);
        return height - (int)(( (y - getYmin())/(getYmax()-getYmin()) )*height) + (margin[3]) + this.getYOffset();
        
        
    }
    
    
    public double getYPlotPosn(int y){
        
        //int width = this.getWidth() - 2*margin;
        int height = this.getHeight() - (margin[2] + margin[3]);
        
        return getYmax() - (y - (margin[3]) - this.getYOffset())*(getYmax()-getYmin())/height + getYmin();
        
        
    }
    
    public double getXmin() {
        return xmin;
    }
    
    public void setXmin(double xmin) {
        this.xmin = xmin;
    }
    
    public double getXmax() {
        return xmax;
    }
    
    public void setXmax(double xmax) {
        this.xmax = xmax;
    }
    
    public double getYmin() {
        return ymin;
    }
    
    public void setYmin(double ymin) {
        this.ymin = ymin;
    }
    
    public double getYmax() {
        return ymax;
    }
    
    public void setYmax(double ymax) {
        this.ymax = ymax;
    }
    
    public double[] getXVals() {
        return xVals;
    }
    
    public void setVals(double[] xVals,double[] yVals) {
        this.xVals = xVals;
        this.yVals = yVals;
        this.nvals = xVals.length;
        
        
        for(double j : xVals){
            if(j > xmax)xmax =  j;
            if(j < xmin)xmin = j;
            
        }
        
        for(double j : yVals){
            if(j > ymax)ymax =  j;
            if(j < ymin)ymin = j;
        }
        
    }
    
    public double[] getYVals() {
        return yVals;
    }
    
    
    public int getNpoints() {
        return npoints;
    }
    
    public int[] getMargin() {
        return margin;
    }
    
    public void setMargin(int[] margin) {
        this.margin = margin;
    }
    
    public double[] getXPoints() {
        return xPoints;
    }
    
    public void setPoints(double[] xPoints,double[] yPoints) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.npoints = xPoints.length;
        for(double j : xPoints){
            if(j > xmax)xmax =  j;
            if(j < xmin)xmin = j;
            
        }
        
        for(double j : yPoints){
            if(j > ymax)ymax =  j;
            if(j < ymin)ymin = j;
        }
    }
    
    public double[] getYPoints() {
        return yPoints;
    }
    
    public String getXLabel() {
        return xLabel;
    }
    
    public void setXLabel(String xLabel) {
        this.xLabel = xLabel;
    }
    
    public String getYLabel() {
        return yLabel;
    }
    
    public void setYLabel(String yLabel) {
        this.yLabel = yLabel;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public boolean isJoinDots() {
        return joinDots;
    }
    
    public void setJoinDots(boolean joinDots) {
        this.joinDots = joinDots;
    }
    
    public int getXOffset(){
        if(this.image){
            return this.imgXOffset;
        } else return 0;
    }
    
    public int getYOffset(){
        if(this.image)return this.imgYOffset;
        else return 0;
    }
    
    public int getWidth() {
        int retValue;
        if(this.image){
            retValue = imgWidth;
        } else retValue = super.getWidth();
        return retValue;
    }
    
    public int getHeight() {
        int retValue;
        
        if(this.image){
            retValue = imgHeight;
        } else retValue = super.getHeight();
        
        return retValue;
    }
    
    public LineStyle getLinestyle() {
        return linestyle;
    }
    
    public void setLinestyle(LineStyle linestyle) {
        this.linestyle = linestyle;
    }
    
    
    public void addClickNotifyable(ClickNotifyable cn){
        this.notifyList.add(cn);
    }
    
    public String toString(){
        return this.title;
    }
    
    public boolean isForceImage() {
        return forceImage;
    }
    
    public void setForceImage(boolean forceImage) {
        this.forceImage = forceImage;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(120, 120));
        setPreferredSize(new java.awt.Dimension(120, 120));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

    }// </editor-fold>//GEN-END:initComponents
    
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        System.out.println(this.getXPlotPosn(evt.getX())+" "+this.getYPlotPosn(evt.getY()));
        for(ClickNotifyable cn : this.notifyList){
            cn.clicked(this.getXPlotPosn(evt.getX()),this.getYPlotPosn(evt.getY()),this);
        }
    }//GEN-LAST:event_formMouseClicked
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
