/*
 * Copyright (C) 2005-2008 Michael Keith, Australia Telescope National Facility, CSIRO
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

/**
 *
 * @author kei041
 */
public class EncodedTwoDimArray {

    double[][] doubleArr;
    int[][] intArr;
    double max, min;
    int intScale = 255;

    public double[][] getDoubleArr() {
        if (doubleArr == null) {
            doubleArr = new double[intArr.length][intArr[0].length];
            for (int x = 0; x < doubleArr.length; x++) {
                for (int y = 0; y < doubleArr[0].length; y++) {
                    doubleArr[x][y] = (((double) intArr[x][y]) / (double) intScale) * (max - min) + min;
                }
            }
        }
        return doubleArr;
    }

    public void setDoubleArr(double[][] doubleArr) {
        this.doubleArr = doubleArr;
        this.intArr = null;
        this.max = -Double.MAX_VALUE;
        this.min = Double.MAX_VALUE;
        for (int x = 0; x < doubleArr.length; x++) {
            for (int y = 0; y < doubleArr[0].length; y++) {
                if (doubleArr[x][y] > max) {
                    max = doubleArr[x][y];
                }
                if (doubleArr[x][y] < min) {
                    min = doubleArr[x][y];
                }
            }
        }
    }

    public int[][] getIntArr() {
        if (intArr == null) {
            intArr = new int[doubleArr.length][doubleArr[0].length];
            for (int x = 0; x < doubleArr.length; x++) {
                for (int y = 0; y < doubleArr[0].length; y++) {

                    intArr[x][y] = (int) ((doubleArr[x][y] - min) / (max - min) * intScale);
                }
            }
        }
        return intArr;
    }

    public void setIntArr(int[][] intArr) {
        this.intArr = intArr;
        this.doubleArr = null;
        this.max = 1;
        this.min = -1;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public int getIntScale() {
        return intScale;
    }

    public void setIntScale(int intScale) {
        this.intScale = intScale;
    }
}
