package ch.geeq.connectors;

import ch.geeq.modbus.ModbusReg;
import ch.geeq.modbus.Utility;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 3/17/17
 */
public class ModbusConnector extends Thread {
    private static ModbusConnector instance;

    private HashMap<Integer, ModbusReg> transactions = new HashMap<>();
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
               // ModbusReader.getInstance(this).start();
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



    //used to start a new transaction
    public void sendTransaction(ModbusReg t)
    {

        //add the transaction to the list
        transactions.put(id, t);



        //TODO: compute MBAP header and get PDU

        byte[] pdu = t.getPDU();
        byte[] mbap = new byte[7]; //create an array for the MBAP
    
        Utility.addNumber(mbap, 0, id);                  //set the id of the transaction
        Utility.addNumber(mbap, 2, 0);              //use the modbus protocol so use 0
        Utility.addNumber(mbap, 4, pdu.length+1);   //set the length to the lenght of the PDU +1 for the unit identifier
        mbap[6]=(byte)t.getRtuAddress();                       //set the rtu address

//        mbap = Utility.reverse(mbap);
//        pdu = Utility.reverse(pdu);
        System.out.println("mbap : "+mbap[6]+" "+mbap[5]+" "+mbap[4]+" "+mbap[3]+" "+mbap[2]+" "+mbap[1]+" "+mbap[0]);
        System.out.println("pdu : "+pdu[4]+" "+pdu[3]+" "+pdu[2]+" "+pdu[1]+" "+pdu[0]);
        
       // System.out.println("Sending transaction: " + id + " mbap: " + Utility.getHexString(mbap,0,mbap.length) + " pdu: " + Utility.getHexString(pdu,0,pdu.length));
        
        try {
            socket.getOutputStream().write(Utility.concatBytes(mbap,pdu));
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //when sent increment the id from all
        id++;
    }

    //get the transaction
    public void recieveTransaction()
    {
        //TODO: request a bit to recieve


    }
}
