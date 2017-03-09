package ch.geeq.connectors;

import ch.geeq.datapoint.BinaryDataPoint;
import ch.geeq.datapoint.DataPoint;
import ch.geeq.datapoint.FloatDataPoint;
import ch.geeq.modbus.Utility;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Timer;
import java.util.TimerTask;

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

        //Schedule a push of the datas
        Timer t = new Timer();
        t.scheduleAtFixedRate(new PushTask(), 0, 3000);
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
     * create the http request for the database  and store it to be pushed later
     *
     * @param label Label
     * @param value Binary value
     */
    private void pushToDB(String label, boolean value)
    {
        s+=label+" value="+value  + " " + new java.util.Date().getTime() +"\n";
    }
    
    int i = 0;
    
    /**
     * create the http request for the database  and store it to be pushed later
     *
     * @param label Label
     * @param value Float value
     */
    private void pushToDB(String label, float value)
    {
        s += label + " value=" + value + " " + new java.util.Date().getTime() + "\n";
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
            System.out.println(s);
            if(rp==200||rp==204)
               System.out.println(rp);
    
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            s="";
        }
    }

    public static class PushTask extends TimerTask
    {
        @Override
        public void run() {

            getInstance().push();
        }
    }
    
}
