/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bookkeepr.xml.display;

import pulsarhunter.displaypanels.*;

/**
 *
 * @author kei041
 */
public interface GenericCandidatePlot {

    public void setTitleText(String[] lines);

    public void setSections(String[] sectionNames);

    public void addProfilePlot(MKPlot plot, int sectionNumber);

    public void addDmPlot(MKPlot plot, int sectionNumber);

        public void addPDmPlot(MKPlot plot, int sectionNumber);

    public void addAccnPlot(MKPlot plot, int sectionNumber);

    public void addSubIntPlot(MKPlot plot, int sectionNumber);

    public void addSubBand(MKPlot plot, int sectionNumber);

    public void setDescText(String[][] text);
    
    public void generate();
}
