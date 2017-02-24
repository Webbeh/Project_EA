package ch.geeq.datapoint.connectors;

import ch.geeq.datapoint.DataPoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class DataBaseConnector {
    private DataPoint dp;
    
    private DataBaseConnector()
    {
    
    }
    public DataBaseConnector getInstance()
    {
        return this;
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
