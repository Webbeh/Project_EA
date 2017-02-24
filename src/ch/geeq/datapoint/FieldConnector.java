package ch.geeq.datapoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class FieldConnector {
    private DataPoint dp;
    
    private FieldConnector()
    {
    
    }
    
    public FieldConnector getInstance()
    {
        return this;
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
