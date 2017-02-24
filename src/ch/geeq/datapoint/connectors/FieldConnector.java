package ch.geeq.datapoint.connectors;

import ch.geeq.datapoint.DataPoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class FieldConnector {
    private DataPoint dp;

    private static FieldConnector instance;

    private FieldConnector()
    {
    
    }
    
    public FieldConnector getInstance(){

        if(instance == null)
        {
            instance = new FieldConnector();
        }
        return instance;
    }
    
    public void onNewValue(DataPoint dp)
    {
        this.dp = dp;
    }
    
    public void pushToField(String label, boolean value)
    {
    
    }
    
    public void pushToField(String label, float value)
    {
    
    }
}
