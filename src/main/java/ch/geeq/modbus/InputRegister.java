package ch.geeq.modbus;

import ch.geeq.connectors.ModbusConnector;
import ch.geeq.datapoint.FloatDataPoint;


/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 3/2/17
 */
public class InputRegister {
    private int inputRegisterAddress, rtuAddress;
    private FloatDataPoint itsFloatDataPoint;
    private String label;
    
    public InputRegister(String label, int rtuAddress, int regAddress) {
        this.rtuAddress=rtuAddress;
        this.inputRegisterAddress=regAddress;
        this.label=label;

        itsFloatDataPoint = new FloatDataPoint(label, false);
    }

    public void read() {
        ModbusConnector mc = ModbusConnector.getInstance();
        try {
            float f = mc.readFloat(rtuAddress, inputRegisterAddress);
            itsFloatDataPoint.setValue(f);
        } catch (NullPointerException e)
        {
            mc.error();
            System.out.println("The connection ended unexpectedly.");
        }
    }
    
    public FloatDataPoint getDataPoint() {
        return itsFloatDataPoint;
    }
}
