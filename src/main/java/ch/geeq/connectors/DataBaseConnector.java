package ch.geeq.connectors;

import ch.geeq.datapoint.BinaryDataPoint;
import ch.geeq.datapoint.DataPoint;
import ch.geeq.datapoint.FloatDataPoint;
import ch.geeq.modbus.Utility;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class DataBaseConnector
{

    //Settings
    private final static String URL = "http://vlesdi.hevs.ch:8086/write?db=";
    private final static String db_name = "SIn16";
    private final static String username = "SIn16";
    private final static String password = Utility.md5sum(username);


    private static DataBaseConnector instance;


    private HttpURLConnection connection;
    private InputStreamReader inputStream;
    private OutputStreamWriter outputStream;
    private DataBaseConnector()
    {
        URL url;
        try
        {
            //Url scheme : http://username:password@url:port/write?db=dbname
            url = new URL(URL + db_name);
            connection =(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "binary/octect-stream");
            String userpass = username+":"+password;
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
            connection.setRequestProperty("Authorization", basicAuth);
            connection.setDoOutput(true);

            //Instanciate IO buffers
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
    
    /**
     * Checks if the datapoint is binary or float
     * @param dp The datapoint
     */
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
    
    /**
     * Push binary data to the database
     * @param label Label
     * @param value Binary value
     */
    private void pushToDB(String label, boolean value)
    {
        try
        {
            outputStream.write(label + " value=" + value);
            outputStream.flush();
    
            if(connection.getResponseCode()==200 || connection.getResponseCode()==204)
                System.out.println("Yes !");
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    
    int i = 0;
    /**
     * Push float data to the database
     * @param label Label
     * @param value Float value
     */
    private void pushToDB(String label, float value)
    {
        try {
            String t = label + " value=" + value;
            outputStream.write(t);
            outputStream.flush();
            System.out.print(t);
            if (connection.getResponseCode() == 204 || connection.getResponseCode() == 200)
                System.out.println(" ; Data n° "+i+++" logged !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
