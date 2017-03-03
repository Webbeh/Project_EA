package ch.geeq.connectors;

import ch.geeq.datapoint.BinaryDataPoint;
import ch.geeq.datapoint.DataPoint;
import ch.geeq.datapoint.FloatDataPoint;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class DataBaseConnector
{

    private static DataBaseConnector instance;

    private DataBaseConnector()
    {

    }

    public static DataBaseConnector getInstance()
    {

        if (instance == null)
        {
            instance = new DataBaseConnector();
        }
        return instance;
    }

    public void onNewValue(DataPoint dp)
    {
        if (dp instanceof BinaryDataPoint)
        {
            pushToDB(dp.getLabel(), ((BinaryDataPoint) dp).getValue());
        }
        else if (dp instanceof FloatDataPoint)
        {
            pushToDB(dp.getLabel(), ((FloatDataPoint) dp).getValue());
        }
    }

    private void pushToDB(String label, boolean value)
    {
        System.out.println("DB:" +label + ":" + value);
    }

    private void pushToDB(String label, float value)
    {
        System.out.println("DB:" +label + ":" + value);
    }
}
