/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bookkeepr.xml.display;

import bookkeepr.xml.StringConvertable;
import bookkeepr.xmlable.RawCandidate;
import bookkeepr.xmlable.RawCandidateSection;
import java.awt.Color;
import java.util.Formatter;
import pulsarhunter.Convert;
import pulsarhunter.displaypanels.MKPlot.LineStyle;
import pulsarhunter.displaypanels.PlotOneDim;
import pulsarhunter.displaypanels.PlotTwoDim;
import pulsarhunter.jreaper.Colourmap;

/**
 *
 * @author kei041
 */
public class GenerateRawCandidatePlot {

    public static void generate(GenericCandidatePlot paper, RawCandidate rawCandidate, Colourmap colourmap, Color c1, Color c2) {
        String[] header = new String[3];

        StringBuffer line = new StringBuffer();
        line.append("ID: " + StringConvertable.ID.toString(rawCandidate.getId()));
        line.append("   RA:" + rawCandidate.getCoordinate().getRA().toString(false));
        line.append("   Dec:" + rawCandidate.getCoordinate().getDec().toString(false));

        line.append("   Gl:" + round(rawCandidate.getCoordinate().getGl(), 100));
        line.append("   Gb:" + round(rawCandidate.getCoordinate().getGb(), 100));
        line.append("   MJD:" + round(rawCandidate.getMjdStart(), 100));

        header[0] = line.toString();




        line = new StringBuffer();

        line.append("ObsFreq:" + round(rawCandidate.getCentreFreq(), 10) + "MHz");
        line.append("   Tobs:" + round(rawCandidate.getTobs(), 1) + "s");
        line.append("   Tsamp:" + (int) (rawCandidate.getOptimisedSec().getTsamp()) + "us");
        line.append("   SourceID:" + rawCandidate.getSourceId());
        line.append("   Telescope:" + rawCandidate.getTelescope());


        header[1] = line.toString();

        line = new StringBuffer();



        line.append("SpecSNR:" + round(rawCandidate.getSpectralSnr(), 100));
        line.append("   ReconSNR:" + round(rawCandidate.getReconstructedSnr(), 100));

//        if (rawCandidate.getInitialSec().getExtraValue("HFOLD") != null) {
//            line.append("   H-Fold:" + rawCandidate.getInitialSec().getExtraValue("HFOLD"));
//        }
//
//        if (rawCandidate.getHeader().getExtraValue("ZAP") != null) {
//            line.append("   Zap:" + rawCandidate.getHeader().getExtraValue("ZAP"));
//        }


        header[2] = line.toString();

        paper.setTitleText(header);


        String[] sections = rawCandidate.getKeys().toArray(new String[]{});

        paper.setSections(sections);

        for (int i = 0; i < sections.length; i++) {

            RawCandidateSection sec = rawCandidate.getSections().get(sections[i]);

            /****************
             *   Profiles   *
             ****************/
            {
                if (sec.getEncodedProfile() != null) {
                    double[] prof = sec.getEncodedProfile().getDoubleArr();
                    int nbins = prof.length;
                    double[] yaxis = Convert.wrapDoubleArr(prof, 1.5);
                    double[] xaxis = new double[yaxis.length];
                    for (int j = 0; j < xaxis.length; j++) {
                        xaxis[j] = (double) j / (double) prof.length;
                    }
                    double[] xpoints = new double[0];
                    double[] ypoints = new double[0];

                    if (i == (sections.length - 1)) {

                        double maxVal = 0;
                        int maxposn = 0;
                        for (int j = 0; j < nbins; j++) {
                            if (yaxis[j] > maxVal) {
                                maxVal = yaxis[j];
                                maxposn = j;
                            }

                        }


//                        int numP = rawCandidate.getNPulses();
//                        xpoints = new double[numP];
//                        ypoints = new double[numP];
//                        double spacing = (double) nbins / (double) numP;
//                        for (int j = 1; j <= numP; j++) {
//                            int bin = (int) (spacing * j) + maxposn;
//                            while (bin >= nbins) {
//                                bin -= nbins;
//                            }
//                            xpoints[j - 1] = bin / ((double) nbins);
//                            ypoints[j - 1] = 0;
//                        }
                    }

                    paper.addProfilePlot(new PlotOneDim("Profile", "Phase", "", xaxis, yaxis, xpoints, ypoints, c2, c1), i);
                }
            }

            /****************
             * Period / PDM *
             ****************/
            {
                if (sec.getSnrBlock() != null && sec.getSnrBlock().getPeriodIndex().length > 1) {
                    if (sec.getSnrBlock().getDmIndex().length > 1) {
                        // We have PDM to draw...

                        double[][] map_d = sec.getSnrBlock().getPDmPlane(sec.getBestAccn(), sec.getBestJerk());
                        //  int[][] map_i = Convert.doubleArrToIntArr(map_d,0,255,1);

                        double[] periodIdx = sec.getSnrBlock().getPeriodIndex();

                        String pType;
                        double period = 0;
                        if (sec.getSnrBlock().isBarrycenter()) {
                            period = sec.getBestBaryPeriod();
                            pType = "Bary ";
                        } else {
                            period = sec.getBestTopoPeriod();
                            pType = "Topo ";
                        }
                        for (int j = 0; j < periodIdx.length; j++) {
                            periodIdx[j] -= period;
                            periodIdx[j] *= 1000.0;
                        }


                        double[] dmIdx = sec.getSnrBlock().getDmIndex();

                        //  double dmStep = dmIdx[1] - dmIdx[0];
                        //  double pStep = (periodIdx[1] - periodIdx[0])*1000.0;


                        //pdmPanels[i] = new PlotTwoDim("Period-DM Plane","Period","DM",map_i,periodIdx[0]*1000.0,pStep,dmIdx[0],dmStep);
                        paper.addPDmPlot(new PlotTwoDim("Period-DM Plane", pType + "Period Offset from " + (1000.0 * period) + "ms", "DM", periodIdx, dmIdx, map_d, colourmap), i);
                    } else {
                        // We have just Period to draw...
                        double[] xaxis = sec.getSnrBlock().getPeriodIndex();
                        double dm = sec.getBestDm();
                        if (Double.isNaN(dm)) {
                            dm = 0;
                        }
                        double[] yaxis = sec.getSnrBlock().getPeriodCurve(dm, sec.getBestAccn(), sec.getBestJerk());
                        double period;
                        String pType;
                        if (sec.getSnrBlock().isBarrycenter()) {
                            period = sec.getBestBaryPeriod();
                            pType = "Bary ";
                        } else {
                            period = sec.getBestTopoPeriod();
                            pType = "Topo ";
                        }
                        double minV = sec.getBestSnr();
                        for (double v : yaxis) {
                            if (v < minV) {
                                minV = v;
                            }
                        }

                        double width = sec.getBestWidth();
                        if (Double.isNaN(width) || width < 0) {
                            width = rawCandidate.getOptimisedSec().getBestWidth();
                        }

                        double[] model = Convert.generatePeriodCurve(xaxis, period, width, rawCandidate.getTobs());

                        // Convert range to plot range.
                        for (int j = 0; j < model.length; j++) {
                            model[j] = model[j] * (sec.getBestSnr() - minV) + minV;
                        }


                        for (int j = 0; j < xaxis.length; j++) {
                            xaxis[j] -= period;
                            xaxis[j] *= 1000.0;
                        }

                        PlotOneDim panel = new PlotOneDim("Period Curve", pType + "Period Offset from " + (1000.0 * period) + "ms", "SNR", xaxis, model, xaxis, yaxis, c1, c2);
                        panel.setJoinDots(true);
                        panel.setLinestyle(LineStyle.JoinTheDots);

                        paper.addPDmPlot(panel, i);
                    }

                } else {
                    // there are no period info... Put a blank plot for now
                }
            }

            /****************
             *    Subints   *
             ****************/
            {
                if (sec.getEncodedSubintegrations() != null) {
                    double[][] map_d = Convert.wrapDoubleArr(Convert.rotateDoubleArray(sec.getEncodedSubintegrations().getDoubleArr()), 1.5);
                    //int[][]    map_i = Convert.doubleArrToIntArr(map_d,0,255,1.5);



                    paper.addSubIntPlot(new PlotTwoDim("Sub-Integrations", "Bin Number", "Sub-Int Number", map_d, colourmap), i);
                } else {
                    // There is no subints...
                }



            }

            /****************
             *    SubBands   *
             ****************/
            {
                if (sec.getEncodedSubbands() != null) {
                    double[][] map_d = Convert.wrapDoubleArr(Convert.rotateDoubleArray(sec.getEncodedSubbands().getDoubleArr()), 1.5);

                    //   int[][]    map_i = Convert.doubleArrToIntArr(map_d,0,255,1.5);

                    paper.addSubBand(new PlotTwoDim("Sub-Bands", "Bin Number", "Sub-Band Number", map_d, colourmap), i);
                } else {
                    // There is no subbands...
                }



            }

            /****************
             *    DM Curve   *
             ****************/
            {
                if (sec.getSnrBlock() != null && sec.getSnrBlock().getDmIndex().length > 1) {

                    double[] xaxis = sec.getSnrBlock().getDmIndex();

                    double[] yaxis;
                    double period;
                    if (sec.getSnrBlock().isBarrycenter()) {
                        period = sec.getBestBaryPeriod();
                    } else {
                        period = sec.getBestTopoPeriod();
                    }

                    //yaxis = sec.getSnrBlock().getDmCurve(period,sec.getBestAccn(),sec.getBestJerk());
                    yaxis = sec.getSnrBlock().getFlatDmCurve();


                    double width = sec.getBestWidth();
                    if (Double.isNaN(width) || width < 0) {
                        width = rawCandidate.getOptimisedSec().getBestWidth();
                    }
                    double[] model = Convert.generateDmCurve(xaxis, period, width, sec.getBestDm(), rawCandidate.getBandwidth(), rawCandidate.getCentreFreq());
                    for (int j = 0; j < model.length; j++) {
                        model[j] *= sec.getBestSnr();

                    }



                    PlotOneDim plot = new PlotOneDim("DM Curve", "DM", "SNR", xaxis, model, xaxis, yaxis, c1, c2);
                    plot.setJoinDots(true);
                    plot.setLinestyle(LineStyle.JoinTheDots);
                    paper.addDmPlot(plot, i);

                } else {
                    // There is no dm curve...
                }

            }


            /****************
             *  Acc Curve   *
             ****************/
            {
                if (sec.getSnrBlock() != null && sec.getSnrBlock().getAccnIndex().length > 1) {
                    if (sec.getSnrBlock().getJerkIndex().length > 1) {
                        double[][] map_d;
                        if (sec.getSnrBlock().isBarrycenter()) {

                            map_d = sec.getSnrBlock().getAccnJerkPlane(sec.getBestDm(), sec.getBestBaryPeriod());
                        } else {

                            map_d = sec.getSnrBlock().getAccnJerkPlane(sec.getBestDm(), sec.getBestTopoPeriod());
                        }

                        //  int[][] map_i = Convert.doubleArrToIntArr(map_d,0,255,1);

                        double[] acIndex = sec.getSnrBlock().getAccnIndex();
                        double[] jeIndex = sec.getSnrBlock().getJerkIndex();

                        //  double dmStep = dmIdx[1] - dmIdx[0];
                        //  double pStep = (periodIdx[1] - periodIdx[0])*1000.0;


                        //pdmPanels[i] = new PlotTwoDim("Period-DM Plane","Period","DM",map_i,periodIdx[0]*1000.0,pStep,dmIdx[0],dmStep);
                        paper.addAccnPlot(new PlotTwoDim("Accn-Jerk Plane", "Accn (m/s/s)", "Jerk (m/s/s/s)", acIndex, jeIndex, map_d, colourmap), i);

                    } else {
                        // pdot only
                        double[] xaxis = sec.getSnrBlock().getAccnIndex();
                        double[] yaxis;
                        /*
                        if(sec.getSnrBlock().isBarrycenter()){
                        yaxis = sec.getSnrBlock().getAccnCurve(sec.getBestDm(),sec.getBestBaryPeriod(),sec.getBestJerk());
                        } else {
                        yaxis = sec.getSnrBlock().getAccnCurve(sec.getBestDm(),sec.getBestTopoPeriod(),sec.getBestJerk());
                        }
                         */
                        yaxis = sec.getSnrBlock().getFlatAccCurve();

                        PlotOneDim plotOneDim = new PlotOneDim("Accn Curve", "Accn (m/s/s)", "SNR", new double[0], new double[0], xaxis, yaxis, c1, c2);
                        plotOneDim.setJoinDots(true);
                        paper.addAccnPlot(plotOneDim, i);
                    }

                } else if (sec.getSnrBlock() != null && sec.getSnrBlock().getJerkIndex().length > 1) {
                    // pddot only
                    double[] xaxis = sec.getSnrBlock().getJerkIndex();
                    double[] yaxis;
                    if (sec.getSnrBlock().isBarrycenter()) {
                        yaxis = sec.getSnrBlock().getJerkCurve(sec.getBestDm(), sec.getBestBaryPeriod(), sec.getBestAccn());
                    } else {
                        yaxis = sec.getSnrBlock().getJerkCurve(sec.getBestDm(), sec.getBestTopoPeriod(), sec.getBestAccn());
                    }
                    PlotOneDim plotOneDim = new PlotOneDim("Jerk Curve", "Jerk (m/s/s/s)", "SNR", new double[0], new double[0], xaxis, yaxis, c1, c2);

                    plotOneDim.setJoinDots(true);
                    paper.addAccnPlot(plotOneDim, i);
                } else {
                    // There is no pdot or pddot curve...
                }


            }
        }


        String[][] bottomText = new String[10][4];
        bottomText[0][0] = "";
        bottomText[0][1] = "Initial";
        bottomText[0][2] = "Optimised";
        bottomText[0][3] = "";

        bottomText[1][0] = "Bary Period";
        bottomText[1][1] = round(rawCandidate.getInitialSec().getBestBaryPeriod() * 1000, 1e9);
        bottomText[1][2] = round(rawCandidate.getOptimisedSec().getBestBaryPeriod() * 1000, 1e9);
        bottomText[1][3] = "ms";




        bottomText[2][0] = "Bary Freq";
        bottomText[2][1] = round(1.0 / rawCandidate.getInitialSec().getBestBaryPeriod(), 1e9);
        bottomText[2][2] = round(1.0 / rawCandidate.getOptimisedSec().getBestBaryPeriod(), 1e9);
        bottomText[2][3] = "Hz";

        bottomText[3][0] = "Topo Period";
        bottomText[3][1] = round(rawCandidate.getInitialSec().getBestTopoPeriod() * 1000, 1e9);
        bottomText[3][2] = round(rawCandidate.getOptimisedSec().getBestTopoPeriod() * 1000, 1e9);
        bottomText[3][3] = "ms";


        bottomText[4][0] = "Topo Freq";
        bottomText[4][1] = round(1.0 / rawCandidate.getInitialSec().getBestTopoPeriod(), 1e9);
        bottomText[4][2] = round(1.0 / rawCandidate.getOptimisedSec().getBestTopoPeriod(), 1e9);
        bottomText[4][3] = "Hz";

        bottomText[5][0] = "Accn";
        bottomText[5][1] = shortStringOf(rawCandidate.getInitialSec().getBestAccn());
        bottomText[5][2] = shortStringOf(rawCandidate.getOptimisedSec().getBestAccn());
        bottomText[5][3] = "m/s/s";

        bottomText[6][0] = "Jerk";
        bottomText[6][1] = shortStringOf(rawCandidate.getInitialSec().getBestJerk());
        bottomText[6][2] = shortStringOf(rawCandidate.getOptimisedSec().getBestJerk());
        bottomText[6][3] = "m/s/s/s";


        bottomText[7][0] = "DM";
        bottomText[7][1] = round(rawCandidate.getInitialSec().getBestDm(), 100.0);
        bottomText[7][2] = round(rawCandidate.getOptimisedSec().getBestDm(), 100.0);
        bottomText[7][3] = "cm/pc";



        bottomText[8][0] = "Width";
        bottomText[8][1] = round(rawCandidate.getInitialSec().getBestWidth(), 100.0);
        bottomText[8][2] = round(rawCandidate.getOptimisedSec().getBestWidth(), 100.0);
        bottomText[8][3] = "periods";

        bottomText[9][0] = "SNR";
        bottomText[9][1] = round(rawCandidate.getInitialSec().getBestSnr(), 100.0);
        bottomText[9][2] = round(rawCandidate.getOptimisedSec().getBestSnr(), 100.0);
        bottomText[9][3] = "";

        
        paper.setDescText(bottomText);
        paper.generate();

    }

    private static String round(double d, double r) {
        if (Double.isNaN(d)) {
            return "N/A";
        } else {
            return Double.toString(((long) (d * r)) / r);
        }
    }

    private static String shortStringOf(double d) {

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
}
