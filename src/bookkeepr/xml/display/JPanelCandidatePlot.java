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
 * PHCFPlot.java
 *
 * Created on 25 January 2007, 16:47
 */
package bookkeepr.xml.display;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pulsarhunter.displaypanels.MKPlot;
import pulsarhunter.displaypanels.MultiPlotPanel;

/**
 *
 * @author  mkeith
 */
public class JPanelCandidatePlot extends javax.swing.JPanel implements GenericCandidatePlot {

    private JPanel[] profilePanels;
    private JPanel[] pdmPanels;
    private JPanel[] sintPanels;
    private JPanel[] sbandPanels;
    private JPanel[] dmCurvePanels;
    private JPanel[] pdotCurvePanels;
    private JPanel bottomText;
    private String[] sections;

    /** Creates new form PHCFPlot */
    public JPanelCandidatePlot() {

        initComponents();
    }

    public void setSections(String[] sections) {
        this.profilePanels = new JPanel[sections.length];
        this.pdmPanels = new JPanel[sections.length];
        this.sintPanels = new JPanel[sections.length];
        this.sbandPanels = new JPanel[sections.length];
        this.dmCurvePanels = new JPanel[sections.length];
        this.pdotCurvePanels = new JPanel[sections.length];
        this.sections = sections;
    }

    public void setProfilePlot(MKPlot plot, int section) {
        this.profilePanels[section] = plot;
    }

    public void addAccnPlot(MKPlot plot, int sectionNumber) {
        pdotCurvePanels[sectionNumber] = plot;
    }

    public void addDmPlot(MKPlot plot, int sectionNumber) {
        dmCurvePanels[sectionNumber] = plot;
    }

    public void addProfilePlot(MKPlot plot, int sectionNumber) {
        profilePanels[sectionNumber] = plot;
    }

    public void addSubBand(MKPlot plot, int sectionNumber) {
        sbandPanels[sectionNumber] = plot;
    }

    public void addSubIntPlot(MKPlot plot, int sectionNumber) {
        sintPanels[sectionNumber] = plot;
    }
    
    public void addPDmPlot(MKPlot plot, int sectionNumber) {
           pdmPanels[sectionNumber] = plot;
    }

    public void setDescText(String[][] text) {
         bottomText = new JPanel();
        bottomText.setLayout(new GridLayout(text.length, text[0].length));

        for (String[] line : text) {
            for (String word : line) {
                bottomText.add(new JLabel(word));
            }
        }
        int i = 0;
        for (Component c : bottomText.getComponents()) {
            c.setFont(new java.awt.Font("Dialog", 0, 10));
            if ((int) (i / 4.0) == (double) i / 4.0) {
                c.setFont(new java.awt.Font("Dialog", Font.BOLD, 10));
            }
            i++;
        }

        bottomText.getComponent(1).setFont(new java.awt.Font("Dialog", Font.BOLD, 10));
        bottomText.getComponent(
                2).setFont(new java.awt.Font("Dialog", Font.BOLD, 10));

        bottomText.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bottomText.setPreferredSize(
                new Dimension(120, 120));



       

    }

    public void setTitleText(String[] lines) {
        if (lines.length > 0) {
            this.jLabel_header_1.setText(lines[0]);
        }

        if (lines.length > 1) {
            this.jLabel_header_2.setText(lines[1]);
        }
        if (lines.length > 2) {
            this.jLabel_header_3.setText(lines[2]);
        }
    }

    public void generate() {
     
        MultiPlotPanel profileMpp = new MultiPlotPanel("Profile", sections, profilePanels, profilePanels.length - 1);
        MultiPlotPanel pdmMpp = new MultiPlotPanel("Period/Dm", sections, pdmPanels, pdmPanels.length - 1);
        MultiPlotPanel sintMpp = new MultiPlotPanel("Sub Integrations", sections, sintPanels, sintPanels.length - 1);
        MultiPlotPanel sbandMpp = new MultiPlotPanel("Sub Bands", sections, sbandPanels, sbandPanels.length - 1);
        MultiPlotPanel dmcMpp = new MultiPlotPanel("Dm Curve", sections, dmCurvePanels, 0);
        MultiPlotPanel pdotMpp = new MultiPlotPanel("Accn/Jerk", sections, pdotCurvePanels, 0);

        // right
        this.jPanel_main_right.add(pdmMpp);


        this.jPanel_main_right.add(dmcMpp);


        this.jPanel_main_right.add(pdotMpp);
        // left


        this.jPanel_main_left.add(sbandMpp);


        this.jPanel_main_left.add(sintMpp);


        this.jPanel_main_left.add(profileMpp);
         this.jPanel_main_right.add(bottomText);
        this.validate();

    }

    private void close() {
        if (this.getParent() instanceof JFrame) {
            ((JFrame) this.getParent()).setVisible(false);
            ((JFrame) this.getParent()).dispose();
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_header = new javax.swing.JPanel();
        jLabel_header_1 = new javax.swing.JLabel();
        jLabel_header_2 = new javax.swing.JLabel();
        jLabel_header_3 = new javax.swing.JLabel();
        jPanel_main = new javax.swing.JPanel();
        jPanel_main_left = new javax.swing.JPanel();
        jPanel_main_right = new javax.swing.JPanel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        jPanel_header.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_headerMouseClicked(evt);
            }
        });
        jPanel_header.setLayout(new javax.swing.BoxLayout(jPanel_header, javax.swing.BoxLayout.Y_AXIS));

        jLabel_header_1.setText("jLabel1");
        jPanel_header.add(jLabel_header_1);

        jLabel_header_2.setText("jLabel2");
        jPanel_header.add(jLabel_header_2);

        jLabel_header_3.setText("jLabel1");
        jPanel_header.add(jLabel_header_3);

        add(jPanel_header, java.awt.BorderLayout.NORTH);

        jPanel_main.setLayout(new java.awt.GridLayout(1, 2));

        jPanel_main_left.setLayout(new javax.swing.BoxLayout(jPanel_main_left, javax.swing.BoxLayout.Y_AXIS));
        jPanel_main.add(jPanel_main_left);

        jPanel_main_right.setLayout(new javax.swing.BoxLayout(jPanel_main_right, javax.swing.BoxLayout.Y_AXIS));
        jPanel_main.add(jPanel_main_right);

        add(jPanel_main, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        close();
    }//GEN-LAST:event_formMouseClicked

    private void jPanel_headerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_headerMouseClicked
        close();
    }//GEN-LAST:event_jPanel_headerMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_header_1;
    private javax.swing.JLabel jLabel_header_2;
    private javax.swing.JLabel jLabel_header_3;
    private javax.swing.JPanel jPanel_header;
    private javax.swing.JPanel jPanel_main;
    private javax.swing.JPanel jPanel_main_left;
    private javax.swing.JPanel jPanel_main_right;
    // End of variables declaration//GEN-END:variables

    
}
