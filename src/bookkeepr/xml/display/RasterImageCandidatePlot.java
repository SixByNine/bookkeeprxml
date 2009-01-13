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
 * PHCFImagePlot.java
 *
 * Created on 01 February 2007, 12:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bookkeepr.xml.display;

import pulsarhunter.displaypanels.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Formatter;

/**
 *
 * @author mkeith
 */
public class RasterImageCandidatePlot implements GenericCandidatePlot {

    private BufferedImage img;
    private int height,  width;
    private Graphics2D g;
    private int nSections;

    /** Creates a new instance of PHCFImagePlot */
    public RasterImageCandidatePlot(BufferedImage img) {
        this.img = img;
        height = img.getHeight();
        width = img.getWidth();
        g = (Graphics2D) img.getGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.setColor(new Color(0, 0, 0));

    }

    public void setTitleText(String[] lines) {
        if (lines.length > 0) {
            g.drawString(lines[0], 5, 10);
        }
        if (lines.length > 1) {
            g.drawString(lines[1], 5, 20);
        }
        if (lines.length > 2) {
            g.drawString(lines[2], 5, 30);
        }
    }

    public void setSections(String[] sectionNames) {
        this.nSections = sectionNames.length;
    }

    public void addProfilePlot(MKPlot plot, int sectionNumber) {
        if (sectionNumber == this.nSections - 1) {

            int x = 0;
            int y = (int) (height * 0.70);
            int xSize = (int) (width * 0.5);
            int ySize = (int) (height * 0.30);
            Graphics2D graphics = (Graphics2D) g.create(x, y, xSize, ySize);
            plot.paintImage(graphics, xSize, ySize);
        }

    }

    public void addDmPlot(MKPlot plot, int sectionNumber) {
        if (sectionNumber == 0) {

            int x = (int) (width * 0.5);
            int y = (int) (height * 0.30);
            int xSize = (int) (width * 0.5);
            int ySize = (int) (height * 0.25);
            Graphics2D graphics = (Graphics2D) g.create(x, y, xSize, ySize);
            plot.paintImage(graphics, xSize, ySize);

        }
    }

    public void addPDmPlot(MKPlot plot, int sectionNumber) {
        if (sectionNumber == this.nSections - 1) {

            int x = (int) (width * 0.5);
            int y = (int) (height * 0.05);
            int xSize = (int) (width * 0.5);
            int ySize = (int) (height * 0.25);
            Graphics2D graphics = (Graphics2D) g.create(x, y, xSize, ySize);
            plot.paintImage(graphics, xSize, ySize);

        }

    }

    public void addAccnPlot(MKPlot plot, int sectionNumber) {
        if (sectionNumber == 0) {

            int x = (int) (width * 0.5);
            int y = (int) (height * 0.55);
            int xSize = (int) (width * 0.5);
            int ySize = (int) (height * 0.25);
            Graphics2D graphics = (Graphics2D) g.create(x, y, xSize, ySize);
            plot.paintImage(graphics, xSize, ySize);

        }
    }

    public void addSubIntPlot(MKPlot plot, int sectionNumber) {
        if (sectionNumber == this.nSections - 1) {

            int x = 0;
            int y = (int) (height * 0.30);
            int xSize = (int) (width * 0.5);
            int ySize = (int) (height * 0.40);
            Graphics2D graphics = (Graphics2D) g.create(x, y, xSize, ySize);
            plot.paintImage(graphics, xSize, ySize);
        }
    }

    public void addSubBand(MKPlot plot, int sectionNumber) {
        if (sectionNumber == this.nSections - 1) {
            int x = 0;
            int y = (int) (height * 0.05);
            int xSize = (int) (width * 0.5);
            int ySize = (int) (height * 0.25);
            Graphics2D graphics = (Graphics2D) g.create(x, y, xSize, ySize);

            plot.paintImage(graphics, xSize, ySize);
        }
    }

    public void setDescText(String[][] text) {
        int x = (int) (width * 0.5);
        int y = (int) (height * 0.80);
        int xSize = (int) (width * 0.5);
        int ySize = (int) (height * 0.20);
        Graphics2D graphics = (Graphics2D) g.create(x, y, xSize, ySize);
        int[] col = new int[]{2, 82, 175, 265};
        int[] row = new int[]{10, 23, 33, 46, 56, 69, 79, 89, 99, 109};




        for (int i = 0; i < text.length; i++) {
            for (int j = 0; j < text[i].length; j++) {
                if (i * j == 0) {
                    graphics.setFont(new Font("Monospaced", Font.BOLD, 10));
                } else {
                    graphics.setFont(new Font("Monospaced", 0, 10));
                }
                graphics.drawString(text[i][j], col[j],row[i]);



            }


        }
    }

    /**
     * Plot layout
     * 5% reserved for header
     *
     * Left side
     * 25% Sub bands
     * 40% Sub ints
     * 30% profile
     *
     * Right Side
     * 25% Pdm
     * 25% dmcurve
     * 25% accurve
     * 20% params
     *
     */
    public void generate() {
    }

    private String shortStringOf(double d) {

        StringBuilder build = new StringBuilder();
        Formatter formater = new Formatter(build);
        if (d == 0) {
            build.append("0.00");
        } else if (Math.abs(d) > 1000) {
            formater.format("%5.4e", d);
        } else if (Math.abs(d) < 0.01) {
            formater.format("%5.4e", d);
        } else {
            formater.format("%3.4f", d);
        }

        return build.toString();
    }

    private String round(double d, double r) {
        if (d == -1) {
            return "N/A";
        } else {
            return Double.toString(((long) (d * r)) / r);
        }
    }
}
