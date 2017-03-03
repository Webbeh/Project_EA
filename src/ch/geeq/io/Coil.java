package ch.geeq.io;

import ch.geeq.datapoint.BinaryDataPoint;
import ch.geeq.datapoint.DataPoint;
import ch.geeq.datapoint.connectors.ModbusConnector;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 3/2/17
 */
public class Coil {
    private int coilAddress, rtuAddress;
    private BinaryDataPoint itsBinaryDataPoint;
    private String label;
    
    public Coil(String label, int rtuAddress, int coilAddress) {
        this.rtuAddress=rtuAddress;
        this.coilAddress=coilAddress;
        
        itsBinaryDataPoint = new BinaryDataPoint(label, true);
    }
    
    public void write() {
        ModbusConnector.getInstance().writeBinary(rtuAddress,coilAddress,itsBinaryDataPoint.getValue());
    }
    
    public DataPoint getBinaryDatapoint() {
        return itsBinaryDataPoint;
    }
}
