package ch.geeq.modbus;

import ch.geeq.datapoint.BinaryDataPoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 3/3/17
 */
public class DiscreteInput {
    private int inputRegisterAddress, rtuAddress;
    private BinaryDataPoint itsBinaryDataPoint;
    private String label;
    
    public DiscreteInput(String label, int rtuAddress, int regAddress) {
        this.rtuAddress=rtuAddress;
        this.inputRegisterAddress=regAddress;
        this.label=label;

        itsBinaryDataPoint = new BinaryDataPoint(label, false);
    }

    public void read() {
        ModbusConnector mc = ModbusConnector.getInstance();
            Boolean f = mc.readBinary(rtuAddress, inputRegisterAddress);
            itsBinaryDataPoint.setValue(f);
    }
    
    public BinaryDataPoint getDataPoint() {
        return itsBinaryDataPoint;
    }
}
