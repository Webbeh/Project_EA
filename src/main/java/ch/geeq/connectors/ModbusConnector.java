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
    
    public Float readFloat()
    {
        return null;
    }
    
    public Boolean readBinary()
    {
    
    }
    
    public void writeFloat(float data)
    {
    
    }
    
    public void writeBinary(boolean data)
    {
    
    }
}
