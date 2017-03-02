package ch.geeq.datapoint.connectors;

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

    public void onMessage(String label, String value)
    {

    }

    public void pushToWebPages(String label, boolean value)
    {
        System.out.println("Web:" +label + ":" + value);
    }
    
    public void pushToWebPages(String label, float value)
    {
        System.out.println("Web:" +label + ":" + value);
    }
}
