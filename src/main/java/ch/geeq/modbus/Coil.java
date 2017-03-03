package ch.geeq.modbus;

import ch.geeq.connectors.ModbusConnector;
import ch.geeq.datapoint.BinaryDataPoint;

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
    
    public BinaryDataPoint getDatapoint() {
        return itsBinaryDataPoint;
    }
}
