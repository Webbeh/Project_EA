package ch.geeq.modbus;

import ch.geeq.connectors.ModbusConnector;
import ch.geeq.datapoint.FloatDataPoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 3/2/17
 */
public class InputRegister extends ModbusReg{

    public InputRegister(String label, int rtuAddress, int regAddress) {
        super(label, rtuAddress, regAddress);

        itsDataPoint = new FloatDataPoint(label, false);
    }

    @Override
    public void recieve() {

    }

    @Override
    public byte[] getPDU() {
        return new byte[0];
    }


}
