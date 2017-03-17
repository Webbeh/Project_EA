package ch.geeq.modbus;

import ch.geeq.datapoint.BinaryDataPoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 3/3/17
 */
public class DiscreteInput extends ModbusReg {


    public DiscreteInput(String label, int rtuAddress, int regAddress) {
        super(label,rtuAddress,regAddress);

        itsDataPoint = new BinaryDataPoint(label, false);
    }

    @Override
    public void recieve() {

    }

    @Override
    public byte[] getPDU() {
        return new byte[0];
    }

}
