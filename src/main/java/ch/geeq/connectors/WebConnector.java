package ch.geeq.connectors;

import ch.geeq.datapoint.BinaryDataPoint;
import ch.geeq.datapoint.DataPoint;
import ch.geeq.datapoint.FloatDataPoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class WebConnector {


    private static WebConnector instance;

    private WebConnector()
    {
    
    }
    
    public static WebConnector getInstance(){

        if(instance == null)
        {
            instance = new WebConnector();
        }
        return instance;
    }
    
    
    /**
     * Push new value to the web connector
     * @param dp The datapoint
     */
    public void onNewValue(DataPoint dp)
    {
        if (dp instanceof BinaryDataPoint)
        {
            pushToWebPages(dp.getLabel(), ((BinaryDataPoint) dp).getValue());
        }
        else if (dp instanceof FloatDataPoint)
        {
            pushToWebPages(dp.getLabel(), ((FloatDataPoint) dp).getValue());
        }
    }
    
    /**
     * get a message from the web and update the relevant datapoint. The type of value is unclear in the documentation so
     * choose a boolean for ease of use. Might need to be modified depending on the specification of the webpage
     * @param label Label
     * @param value Value to push
     */
    public void onMessage(String label, boolean value)
    {
        BinaryDataPoint dp = (BinaryDataPoint) DataPoint.getDataPointFromLabel(label);
        dp.setValue(value);
    }

    public void pushToWebPages(String label, boolean value)
    {
//        System.out.println("Web:" +label + ":" + value);
    }
    
    public void pushToWebPages(String label, float value)
    {
//        System.out.println("Web:" +label + ":" + value);
    }
}
