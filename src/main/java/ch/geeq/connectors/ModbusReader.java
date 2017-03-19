package ch.geeq.connectors;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 3/17/17
 */
public class ModbusReader extends Thread {
    private static ModbusReader instance;
    private ModbusConnector connector;
    private ModbusReader(ModbusConnector connector)
    {
        this.connector=connector;
    }
    
    public static ModbusReader getInstance(ModbusConnector connector)
    {
        if(instance==null) {
            instance = new ModbusReader(connector);
        }
        return instance;
    }
  


    @Override
    public void run()
    {
        while(true) {
            System.out.println("Test");
       /*     if (!connector.checkConnect()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }*/
            try {
                DataInputStream dis = new DataInputStream(connector.getSocket().getInputStream());
                String line = dis.readUTF();
                System.out.println("Read : "+line);
            } catch (IOException e) {
                e.printStackTrace();
            }
    
        }
    }
}
