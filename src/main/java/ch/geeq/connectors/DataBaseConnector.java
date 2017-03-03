package ch.geeq.connectors;

import ch.geeq.datapoint.BinaryDataPoint;
import ch.geeq.datapoint.DataPoint;
import ch.geeq.datapoint.FloatDataPoint;
import ch.geeq.modbus.Utility;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class DataBaseConnector {
    
    //Settings
    private final static String URL = "http://vlesdi.hevs.ch:8086/write?db=";
    private final static String db_name = "SIn16";
    private final static String username = "SIn16";
    private final static String password = Utility.md5sum(username);
    
    
    private static DataBaseConnector instance;
    
    
    private HttpURLConnection connection;
    private DataOutputStream outputStream;
    
    private DataBaseConnector()
    {
    
    }
    
    public static DataBaseConnector getInstance()
    {
    
        if (instance == null) {
            instance = new DataBaseConnector();
        }
        return instance;
    }
    
    /**
     * Checks if the datapoint is binary or float
     *
     * @param dp The datapoint
     */
    public void onNewValue(DataPoint dp)
    {
        if (dp instanceof BinaryDataPoint) {
            pushToDB(dp.getLabel(), ((BinaryDataPoint) dp).getValue());
        } else if (dp instanceof FloatDataPoint) {
            pushToDB(dp.getLabel(), ((FloatDataPoint) dp).getValue());
        }
    }
    
    /**
     * Push binary data to the database
     *
     * @param label Label
     * @param value Binary value
     */
    private void pushToDB(String label, boolean value)
    {
        s+=label+" value="+value+"\n";
    }
    
    int i = 0;
    
    /**
     * Push float data to the database
     *
     * @param label Label
     * @param value Float value
     */
    private void pushToDB(String label, float value)
    {
        s += label + " value=" + value + "\n";
    }
    
    String s = "";
    
    public void push()
    {
        URL url;
        try {
            //Url scheme : http://username:password@url:port/write?db=dbname
            url = new URL(URL + db_name);
            connection = (HttpURLConnection) url.openConnection();
            String userpass = username + ":" + password;
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", basicAuth);
            connection.setRequestProperty("Content-Type", "binary/octect-stream");
    
            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(s);
            outputStream.flush();
            outputStream.close();
            int rp = connection.getResponseCode();
            if(rp==200||rp==204)
                System.out.println(s);
    
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            s="";
        }
    }
    
}
