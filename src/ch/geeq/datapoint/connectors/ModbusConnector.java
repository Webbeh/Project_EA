package ch.geeq.datapoint.connectors;

import java.util.Random;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 3/2/17
 */
public class ModbusConnector {
    
    private static ModbusConnector instance;

    private ModbusConnector()
    {
    
    }
    
    public static ModbusConnector getInstance(){

        if(instance == null)
        {
            instance = new ModbusConnector();
        }
        return instance;
    }
    
    public float readFloat(int rtuAddress, int portAddress)
    {
        return new Random().nextFloat();
    }
    
    public void writeBinary(int rtuAddress, int portAddress, boolean value)
    {
        System.out.println("Coil with rtu #"+rtuAddress+" and address #"+portAddress+". Value to write : "+value);

    }
}
