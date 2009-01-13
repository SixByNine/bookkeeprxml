/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package psrxml;

import bookkeepr.xmlable.Data;
import bookkeepr.xmlable.Psrxml;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author kei041
 */
public class PsrxmlDataReader {

    private Psrxml psrxml;
    private Data dataFile;
    private File currentFile;
    private InputStream in=null;
    private float[][][] lookupTable = new float[8][8][256];

    public PsrxmlDataReader(Psrxml psrxml, Data dataFile) {
        this.psrxml = psrxml;
        this.dataFile = dataFile;
        currentFile = new File(dataFile.getFilename());
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }
    
    

    private float[][] unpackDataBlock(byte[] data) {

        String dataOrder = dataFile.getDataOrder();
        final float[][] res = new float[psrxml.getNumberOfChannels()][data.length / psrxml.getNumberOfChannels()];
        final int nsampPerByte = 8 / dataFile.getBitsPerSample();
        final int bitsPerSamp = dataFile.getBitsPerSample();
        final int nchans = psrxml.getNumberOfChannels();
        final boolean swap_chans = psrxml.getChannelOffset() > 0;
        final int mask;
        switch (dataFile.getBitsPerSample()) {
            case 8:
                mask = 255;
                break;
            case 4:
                mask = 15;
                break;
            case 2:
                mask = 3;
                break;
            case 1:
                mask = 1;

        }
        if (dataOrder.contains("TF")) {
            int chan = 0;
            int samp = 0;
            for (byte b : data) {
                int v = b & 0xFF;
                for (int i = 0; i < nsampPerByte; i++) {
                    if(swap_chans)res[nchans-chan][samp] = lookupTable[bitsPerSamp][i][v];
                    else res[chan][samp] = lookupTable[bitsPerSamp][i][v];
                    chan++;
                    if (chan > psrxml.getNumberOfChannels()) {
                        samp++;
                        chan = 0;
                    }
                }
            }
        }

        return null;
    }

    public float[][] ReadNextBlockAsFloats() throws IOException{
        
        if(this.in == null){
            this.in = new FileInputStream(currentFile);
            this.in.skip(dataFile.getHeaderLength());
        }
        this.in.skip(dataFile.getBlockHeaderLength());
        byte[] barr = new byte[(int)dataFile.getBlockSize()];
        this.in.read(barr);
        return unpackDataBlock(barr);
    }

    void makeLookup(int bitspersamp, boolean firstSampleIsMSB, boolean isSigned) {


        int bitmask;
        int negVal;
        int negConv;
        int i, j, k;
        int x;

//        if (lookupTableValid[bitspersamp]) {
//            freeLookup(bitspersamp);
//        }
        // make the lookup table...
        switch (bitspersamp) {
            case 1:
                bitmask = 0x01;
                negVal = 1;
                negConv = 2;
                break;
            case 2:
                bitmask = 0x03;
                negVal = 2;
                negConv = 4;
                break;
            case 4:
                bitmask = 0x0F;
                negVal = 8;
                negConv = 16;
                break;
            case 8:
                bitmask = 0xFF;
                negVal = 128;
                negConv = 256;
                break;
            default:
                throw new IllegalArgumentException("Cannot unpack data with " + bitspersamp + " bits per sample");
        }
        x = 8 / (bitspersamp) - 1;

        for (i = 0; i < 8 / (bitspersamp); i++) {

            for (j = 0; j < 256; j++) {//=bitspersamp){

                k = j;
                if (firstSampleIsMSB) {
                    k = k >> ((x - i) * bitspersamp);
                } else {
                    k = k >> ((i) * bitspersamp);
                }
                k = k & bitmask;
                if (isSigned && k >= negVal) {
                    k = k - negConv;
                }
                lookupTable[bitspersamp][i][j] = (float) k;
            }
        }

    }
}
