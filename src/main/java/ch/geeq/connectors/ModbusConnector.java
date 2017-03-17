package ch.geeq.connectors;

import java.io.IOException;
import java.net.Socket;

import ch.geeq.modbus.ModbusReg;

import java.util.HashMap;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 3/17/17
 */
public class ModbusConnector extends Thread {
    private static ModbusConnector instance;

    private HashMap<Integer, ModbusReg> transactions;
    private int id = 0;

    private Socket socket;
    private boolean connected=false;
    private String inet=null;
    private int port=0;
    private ModbusConnector()
    {
    }
    
    public static ModbusConnector getInstance()
    {
        if(instance==null) {
            instance = new ModbusConnector();
            instance.start();
        }
        return instance;
    }

    Socket getSocket()
    {
        return socket;
    }

    public boolean connect()
    {
        if(inet == null || port==0)
            return false;
        return connect(inet, port);
    }
    public boolean connect(String inet, int port)
    {
        this.inet=inet;
        this.port=port;

        if(!checkConnect()) {
            try {
                socket.close();
            } catch (Exception ignored) {}
            try {
                socket = new Socket(inet, port);
                socket.setKeepAlive(false);
                connected=true;
                ModbusReader.getInstance(this).start();
                return true;
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }
   
    public boolean checkConnect()
    {
        if(socket==null || !connected) return false;
        // TODO: 3/17/17 Check for modbus availability
        try {
            socket.getOutputStream().write("CC".getBytes());
            return true;
        } catch (IOException e) {
            connected=false;
            return false;
        }
    }

    public Float readFloat(int rtuAddress, int inputRegisterAddress)
    {
        return null;
    }
    
    public Boolean readBinary(int rtuAddress, int inputRegisterAddress)
    {
        return null;
    }
    
    public void writeFloat(float data)
    {
    
    }
    
    public void writeBinary(int rtuAddress, int coilAddress, boolean data)
    {
    
    }

    //used to start a new transaction
    public void sendTransaction(ModbusReg t)
    {

        //add the transaction to the list
        transactions.put(id, t);
        id++;


        //TODO: compute MBAP header and get PDU

    }

    //get the transaction
    public void recieveTransaction()
    {
        //TODO: send a bit to recieve


    }
}
