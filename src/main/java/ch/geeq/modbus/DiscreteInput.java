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
        byte pdu[] = new byte[5];

        pdu[0] = 0x02; //function code to read the input

        //set the address of the register
        Utility.addNumber(pdu,1,regAddress);

        //set value the amount in the read. Only read one

        Utility.addNumber(pdu,3,1);

        return pdu;

    }

}
