package ch.geeq.datapoint.connectors;

import ch.geeq.datapoint.DataPoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class DataBaseConnector {
    private DataPoint dp;

    private static DataBaseConnector instance;

    private DataBaseConnector()
    {
    
    }

    public static DataBaseConnector getInstance()
    {

        if(instance == null)
        {
            instance = new DataBaseConnector();
        }
        return instance;
    }

    public void onNewValue(DataPoint dp)
    {
        this.dp = dp;
    }
    
    private void pushToDB(String label, boolean value)
    {
    
    }
    
    private void pushToDB(String label, float value)
    {
    
    }
}
