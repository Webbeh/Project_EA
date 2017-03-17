package ch.geeq.modbus;

import ch.geeq.datapoint.BinaryDataPoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 3/2/17
 */
public class Coil extends ModbusReg {

    
    /**
     * Creates a new coil
     * @param label The label
     * @param rtuAddress RTU Address
     * @param coilAddress Register Address
     */
    public Coil(String label, int rtuAddress, int coilAddress) {
        super(label,rtuAddress,coilAddress);
        
        itsDataPoint = new BinaryDataPoint(label, true);
    }




    @Override
    public void recieve() {

    }

    @Override
    public byte[] getPDU() {
        byte pdu[] = new byte[5];

        pdu[0] = 5; //function code;

        //set the address of the register
        Utility.addNumber(pdu,1,regAddress);

        //set value
        if(((BinaryDataPoint)itsDataPoint).getValue()) //if on set the bobine
        {
            Utility.addNumber(pdu,3,0xFF00);
        }
        else
        {
            Utility.addNumber(pdu, 3, 0x0000);
        }

        return pdu;
    }
}
