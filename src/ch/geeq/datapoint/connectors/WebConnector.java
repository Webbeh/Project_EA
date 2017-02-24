package ch.geeq.datapoint.connectors;

import ch.geeq.datapoint.DataPoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class WebConnector {
    private DataPoint dp;
    
    private WebConnector()
    {
    
    }
    
    public WebConnector getInstance()
    {
        return this;
    }
    
    public void onNewValue(DataPoint dp)
    {
        this.dp = dp;
    }
    
    public void pushToWebPages(String label, boolean value)
    {
    
    }
    
    public void pushToWebPages(String label, float value)
    {
    
    }
}
