package ch.geeq;

import ch.geeq.datapoint.BinaryDataPoint;
import ch.geeq.datapoint.FloatDataPoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class Project {
    public static void main(String[] args) {
        BinaryDataPoint boolIn = new BinaryDataPoint("boolIn", false);
        BinaryDataPoint boolOut = new BinaryDataPoint("boolOut", true);
        
        FloatDataPoint floatIn = new FloatDataPoint("floatIn", false);
        FloatDataPoint floatOut = new FloatDataPoint("floatOut", true);
        
        boolIn.setValue(true);
        boolOut.setValue(false);
        
        floatIn.setValue(17.4f);
        floatOut.setValue(13.2f);
    

    }
}
