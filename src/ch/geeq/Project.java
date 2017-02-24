package ch.geeq;

import ch.geeq.datapoint.DataPoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class Project {
    public static void main(String[] args) {
        DataPoint boolIn = new DataPoint("boolIn", false);
        DataPoint boolOut = new DataPoint("boolOut", true);
        
        DataPoint floatIn = new DataPoint("floatIn", false);
        DataPoint floatOut = new DataPoint("floatOut", true);
    }
}
