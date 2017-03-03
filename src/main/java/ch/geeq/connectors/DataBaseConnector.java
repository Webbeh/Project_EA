package ch.geeq.connectors;

import ch.geeq.datapoint.BinaryDataPoint;
import ch.geeq.datapoint.DataPoint;
import ch.geeq.datapoint.FloatDataPoint;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class DataBaseConnector
{

    private final static String URL = "vlesdi.hevs.ch:8086/write?db=";
    private final static String db_name = "";
    private final static String username = "";
    private final static String password = "";


    private static DataBaseConnector instance;


    private HttpURLConnection connection;
    private InputStreamReader inputStream;
    private OutputStreamWriter outputStream;
    private DataBaseConnector()
    {
        URL url;
        try
        {
            url = new URL("http://"+username+":"+password+"@"+URL + db_name);
            connection =(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "binary/octect-stream");
            connection.setDoOutput(true);

            inputStream = new InputStreamReader(connection.getInputStream());
            outputStream = new OutputStreamWriter(connection.getOutputStream());

        } catch (Exception e)
        {
            e.printStackTrace();
        }

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
        System.out.println("DB:" + label + ":" + value);

        try
        {
            outputStream.write(label + ": value=" + value);
            char[] buffer = new char[6];
            inputStream.read(buffer,0,6);

            if(buffer.equals("200 OK"))
            {
                System.out.println("Answered correctly");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void pushToDB(String label, float value)
    {
        System.out.println("DB:" +label + ":" + value);
    }
}
