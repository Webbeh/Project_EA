package ch.geeq.datapoint.connectors;

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
    
    public float readFloat(int address)
    {
        return 0f;
    }
    
    public void writeBinary(int address, boolean value)
    {
    
    }
}
