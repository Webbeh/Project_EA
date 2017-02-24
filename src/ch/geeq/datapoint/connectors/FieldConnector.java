package ch.geeq.datapoint.connectors;

import ch.geeq.datapoint.BinaryDataPoint;
import ch.geeq.datapoint.DataPoint;
import ch.geeq.datapoint.FloatDataPoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class FieldConnector {

    private static FieldConnector instance;

    private FieldConnector()
    {
    
    }
    
    public static FieldConnector getInstance(){

        if(instance == null)
        {
            instance = new FieldConnector();
        }
        return instance;
    }
    
    public void onNewValue(DataPoint dp)
    {

        if (dp instanceof BinaryDataPoint)
        {
            pushToField(dp.getLabel(), ((BinaryDataPoint) dp).getValue());
        }
        else if (dp instanceof FloatDataPoint)
        {
            pushToField(dp.getLabel(), ((FloatDataPoint) dp).getValue());
        }
    }
    
    public void pushToField(String label, boolean value)
    {
        System.out.println("Field:" +label + ":" + value);
    }
    
    public void pushToField(String label, float value)
    {
        System.out.println("Field:" +label + ":" + value);
    }
    
}
