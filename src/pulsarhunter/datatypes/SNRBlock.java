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
 * SNRBlock.java
 *
 * Created on 18 January 2007, 17:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package pulsarhunter.datatypes;

import java.util.Arrays;

public class SNRBlock {

    private double[][][][] block;
    private double[] dmIndex;
    private double[] periodIndex;
    private double[] accnIndex;
    private double[] jerkIndex;
    private boolean barrycenter = false;
    private boolean flattenCurves = true;
    private double max = Double.NaN;
    private double min = Double.NaN;

    public SNRBlock(double[] dmIndex, double[] periodIndex, double[] pdotIndex, double[] pddotIndex) {
        this(dmIndex, periodIndex, pdotIndex, pddotIndex, new double[dmIndex.length][periodIndex.length][pdotIndex.length][pddotIndex.length]);

        for (int i = 0; i < dmIndex.length; i++) {
            for (int j = 0; j < periodIndex.length; j++) {
                for (int k = 0; k < pdotIndex.length; k++) {
                    for (int l = 0; l < pddotIndex.length; l++) {
                        block[i][j][k][l] = 0;
                    }
                }
            }
        }
    }

    public SNRBlock(double[] dmIndex, double[] periodIndex, double[] pdotIndex, double[] pddotIndex, double[][][][] block) {
        if (dmIndex.length == 0 || periodIndex.length == 0 || pdotIndex.length == 0 || pddotIndex.length == 0) {
            throw new IllegalArgumentException("Cannot have 0 length indexes in a snr block.");
        }
        this.dmIndex = dmIndex.clone();
        this.periodIndex = periodIndex.clone();
        this.accnIndex = pdotIndex.clone();
        this.jerkIndex = pddotIndex.clone();
        Arrays.sort(this.getDmIndex());
        Arrays.sort(this.getPeriodIndex());
        Arrays.sort(this.getAccnIndex());
        Arrays.sort(this.getJerkIndex());
        this.block = block;

    }

    public void addPoint(double dm, double period, double accn, double jerk, double snr) {

        int x = Arrays.binarySearch(getDmIndex(), dm);
        int y = Arrays.binarySearch(getPeriodIndex(), period);
        int z = Arrays.binarySearch(getAccnIndex(), accn);
        int w = Arrays.binarySearch(getJerkIndex(), jerk);



        x = fixPosn(x, dmIndex, dm);
        y = fixPosn(y, periodIndex, period);
        z = fixPosn(z, accnIndex, accn);
        w = fixPosn(w, jerkIndex, jerk);


        block[x][y][z][w] = snr;

    }
    
    public double getPoint(double dm, double period, double accn, double jerk) {

        int x = Arrays.binarySearch(getDmIndex(), dm);
        int y = Arrays.binarySearch(getPeriodIndex(), period);
        int z = Arrays.binarySearch(getAccnIndex(), accn);
        int w = Arrays.binarySearch(getJerkIndex(), jerk);



        x = fixPosn(x, dmIndex, dm);
        y = fixPosn(y, periodIndex, period);
        z = fixPosn(z, accnIndex, accn);
        w = fixPosn(w, jerkIndex, jerk);


       return block[x][y][z][w];

    }

    private int fixPosn(int i, double[] index, double target) {
        int max = index.length;
        if (i < 0) {
            i = -i - 1;
        }
        if (i >= max) {
            i = max - 1;
        }
        if (i > 0) {
            if (Math.abs(index[i - 1] - target) < Math.abs(index[i] - target)) {
                i--;
            }
        }

        return i;
    }

    public double[] getFlatDmCurve() {
        double[] result = new double[dmIndex.length];
        for (int i = 0; i < dmIndex.length; i++) {
            result[i] = 0;

            for (int y = 0; y < periodIndex.length; y++) {
                for (int z = 0; z < accnIndex.length; z++) {
                    for (int w = 0; w < jerkIndex.length; w++) {
                        if (block[i][y][z][w] > result[i]) {
                            result[i] = block[i][y][z][w];
                        }
                    }
                }
            }

        }
        return result;

    }

    public double[] getDmCurve(double period, double accn, double jerk) {
        int y = Arrays.binarySearch(getPeriodIndex(), period);
        int z = Arrays.binarySearch(getAccnIndex(), accn);
        int w = Arrays.binarySearch(getJerkIndex(), jerk);

        y = fixPosn(y, periodIndex, period);
        z = fixPosn(z, accnIndex, accn);
        w = fixPosn(w, jerkIndex, jerk);
        double[] result = new double[dmIndex.length];
        for (int i = 0; i < dmIndex.length; i++) {
            result[i] = block[i][y][z][w];
        }
        return result;
    }

    public double[] getPeriodCurve(double dm, double accn, double jerk) {

        int x = Arrays.binarySearch(getDmIndex(), dm);
        int z = Arrays.binarySearch(getAccnIndex(), accn);
        int w = Arrays.binarySearch(getJerkIndex(), jerk);

        x = fixPosn(x, dmIndex, dm);
        z = fixPosn(z, accnIndex, accn);
        w = fixPosn(w, jerkIndex, jerk);

        double[] result = new double[periodIndex.length];
        for (int i = 0; i < periodIndex.length; i++) {
            result[i] = block[x][i][z][w];

        }
        return result;

    }

    public double[] getFlatAccCurve() {
        double[] result = new double[accnIndex.length];
        for (int i = 0; i < accnIndex.length; i++) {
            result[i] = 0;

            for (int x = 0; x < dmIndex.length; x++) {
                for (int y = 0; y < periodIndex.length; y++) {
                    for (int w = 0; w < jerkIndex.length; w++) {
                        if (block[x][y][i][w] > result[i]) {
                            result[i] = block[x][y][i][w];
                        }
                    }
                }
            }

        }
        return result;

    }

    public double[] getAccnCurve(double dm, double period, double jerk) {

        int x = Arrays.binarySearch(getDmIndex(), dm);
        int y = Arrays.binarySearch(getPeriodIndex(), period);
        int w = Arrays.binarySearch(getJerkIndex(), jerk);

        x = fixPosn(x, dmIndex, dm);
        y = fixPosn(y, periodIndex, period);
        w = fixPosn(w, jerkIndex, jerk);

        double[] result = new double[accnIndex.length];
        for (int i = 0; i < result.length; i++) {
            //     System.out.println(x+" "+y+" "+i+" "+w);
            result[i] = block[x][y][i][w];
        }
        return result;

    }

    public double[] getJerkCurve(double dm, double period, double accn) {


        int x = Arrays.binarySearch(getDmIndex(), dm);
        int y = Arrays.binarySearch(getPeriodIndex(), period);
        int z = Arrays.binarySearch(getAccnIndex(), accn);

        x = fixPosn(x, dmIndex, dm);
        y = fixPosn(y, periodIndex, period);
        z = fixPosn(z, accnIndex, accn);

        double[] result = new double[jerkIndex.length];
        for (int i = 0; i < jerkIndex.length; i++) {
            result[i] = block[x][y][z][i];
        }
        return result;

    }

    public double[][] getPDmPlane(double accn, double jerk) {

        int z = Arrays.binarySearch(getAccnIndex(), accn);
        int w = Arrays.binarySearch(getJerkIndex(), jerk);

        z = fixPosn(z, accnIndex, accn);
        w = fixPosn(w, jerkIndex, jerk);

        double[][] result = new double[periodIndex.length][dmIndex.length];
        for (int i = 0; i < periodIndex.length; i++) {
            for (int j = 0; j < dmIndex.length; j++) {
                result[i][j] = block[j][i][z][w];
            }
        }
        return result;

    }

    public double[][] getAccnJerkPlane(double dm, double period) {

        int x = Arrays.binarySearch(getAccnIndex(), dm);
        int y = Arrays.binarySearch(getJerkIndex(), period);


        x = fixPosn(x, dmIndex, dm);
        y = fixPosn(y, periodIndex, period);

        double[][] result = new double[accnIndex.length][jerkIndex.length];
        for (int i = 0; i < accnIndex.length; i++) {
            for (int j = 0; j < jerkIndex.length; j++) {
                result[i][j] = block[x][y][i][j];
            }
        }
        return result;

    }

    public double[][][][] getBlock() {
        return block.clone();
    }

    public double[] getDmIndex() {
        return dmIndex.clone();
    }

    public double[] getPeriodIndex() {
        return periodIndex.clone();
    }

    public double[] getAccnIndex() {
        return accnIndex.clone();
    }

    public double[] getJerkIndex() {
        return jerkIndex.clone();
    }

    public boolean isBarrycenter() {
        return barrycenter;
    }

    public void setBarrycenter(boolean barrycenter) {
        this.barrycenter = barrycenter;
    }

    public double getMax() {
        if (Double.isNaN(max)) {

            min = Double.MAX_VALUE;
            max = -Double.MAX_VALUE;
            for (int x = 0; x < this.getDmIndex().length; x++) {
                for (int y = 0; y < this.getPeriodIndex().length; y++) {
                    for (int z = 0; z < this.getAccnIndex().length; z++) {
                        for (int q = 0; q < this.getJerkIndex().length; q++) {
                            if (block[x][y][z][q] > max) {
                                max = block[x][y][z][q];
                            }
                            if (block[x][y][z][q] < min) {
                                min = block[x][y][z][q];
                            }
                        }
                    }
                }
            }
        }
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public synchronized double getMin() {

        if (Double.isNaN(min)) {

            min = Double.MAX_VALUE;
            max = -Double.MAX_VALUE;
            for (int x = 0; x < this.getDmIndex().length; x++) {
                for (int y = 0; y < this.getPeriodIndex().length; y++) {
                    for (int z = 0; z < this.getAccnIndex().length; z++) {
                        for (int q = 0; q < this.getJerkIndex().length; q++) {
                            if (block[x][y][z][q] > max) {
                                max = block[x][y][z][q];
                            }
                            if (block[x][y][z][q] < min) {
                                min = block[x][y][z][q];
                            }
                        }
                    }
                }
            }
        }
        return min;
    }

    public synchronized void setMin(double min) {


        this.min = min;
    }
}

