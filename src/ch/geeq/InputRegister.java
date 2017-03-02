package ch.geeq;

import ch.geeq.datapoint.FloatDataPoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 3/2/17
 */
public class InputRegister {
    private int inputRegisterAddress, rtuAddress;
    private FloatDataPoint itsFloatDataPoint;
    
    public InputRegister(int rtuAddress, int regAddress) {
        this.rtuAddress=rtuAddress;
        this.inputRegisterAddress=regAddress;
    }
    
    public void read() {
    
    }
}
