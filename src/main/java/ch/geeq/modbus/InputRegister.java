package ch.geeq.modbus;

import ch.geeq.datapoint.FloatDataPoint;
import ch.geeq.connectors.ModbusConnector;


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
        System.out.println("Read from Input Register \""+label+"\" with rtu #"+rtuAddress+" and reg #"+inputRegisterAddress+".");
        float f = mc.readFloat(rtuAddress, inputRegisterAddress);
        System.out.println("Value returned : "+f+". Sending to float datapoint.");
        itsFloatDataPoint.setValue(f);
        System.out.println("Value sent to floatdatapoint.");
    }
}
