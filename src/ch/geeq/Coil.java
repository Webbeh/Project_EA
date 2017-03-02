package ch.geeq;

import ch.geeq.datapoint.BinaryDataPoint;
import ch.geeq.datapoint.DataPoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 3/2/17
 */
public class Coil {
    private int coilAddress, rtuAddress;
    private BinaryDataPoint itsBinaryDataPoint;
    
    public Coil(String label, int rtuAddress, int coilAddress) {
        this.rtuAddress=rtuAddress;
        this.coilAddress=coilAddress;
        
        itsBinaryDataPoint = new BinaryDataPoint(label, true);
    }
    
    public void write() {
    
    }
    
    public DataPoint getBinaryDatapoint() {
        return itsBinaryDataPoint;
    }
}
