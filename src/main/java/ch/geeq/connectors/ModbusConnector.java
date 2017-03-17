package ch.geeq.connectors;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 3/17/17
 */
public class ModbusConnector {
    private static ModbusConnector instance;
    
    private ModbusConnector()
    {
    
    }
    
    public static ModbusConnector getInstance()
    {
        if(instance==null)
            instance=new ModbusConnector();
        return instance;
    }
    
    public boolean connect()
    {
        return false;
    }
    
    public Float readFloat(int rtuAddress, int inputRegisterAddress)
    {
        return null;
    }
    
    public Boolean readBinary(int rtuAddress, int inputRegisterAddress)
    {
        return null;
    }
    
    public void writeFloat(float data)
    {
    
    }
    
    public void writeBinary(int rtuAddress, int coilAddress, boolean data)
    {
    
    }
}
