package ch.geeq.modbus;

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
    
    /**
     * Creates a new coil
     * @param label The label
     * @param rtuAddress RTU Address
     * @param coilAddress Register Address
     */
    public Coil(String label, int rtuAddress, int coilAddress) {
        this.rtuAddress=rtuAddress;
        this.coilAddress=coilAddress;
        
        itsBinaryDataPoint = new BinaryDataPoint(label, true);
    }
    
    /**
     * Write data to the coil
     */
    public void write() {
        ModbusConnector.getInstance().writeBinary(rtuAddress,coilAddress,itsBinaryDataPoint.getValue());
    }
    
    public BinaryDataPoint getDatapoint() {
        return itsBinaryDataPoint;
    }
}
