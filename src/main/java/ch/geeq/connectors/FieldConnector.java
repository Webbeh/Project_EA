package ch.geeq.connectors;

import ch.geeq.datapoint.BinaryDataPoint;
import ch.geeq.datapoint.DataPoint;
import ch.geeq.datapoint.FloatDataPoint;
import ch.geeq.modbus.Coil;
import ch.geeq.modbus.DiscreteInput;
import ch.geeq.modbus.InputRegister;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class FieldConnector {
    public static final String ipAddress = "127.0.0.1";
    public static final int port = 1502;
    private static FieldConnector instance;
    final HashMap<DataPoint, InputRegister> inputRegisterHashMap = new HashMap<>();
    final HashMap<DataPoint, Coil> coilsHashMap = new HashMap<>();
    final HashMap<DataPoint, DiscreteInput> discreteInputHashMap = new HashMap<>();
    
    private FieldConnector()
    {
        ModbusConnector.getInstance().connect(ipAddress, port);
        //Schedule a polling of inputs
        Timer t = new Timer();
        //Add delay to wait for objects to be connected.
        t.scheduleAtFixedRate(new FieldConnector.PollTask(), 500, 3000);
    }
    
    public static FieldConnector getInstance(){

        if(instance == null)
        {
            instance = new FieldConnector();
        }
        return instance;
    }
    
    /**
     * Push new values to the coil
     * @param dp
     */
    public void onNewValue(DataPoint dp)
    {
        if (dp instanceof BinaryDataPoint)
        {
            Coil coil = coilsHashMap.get(dp);

            coil.request();

        }
        else if (dp instanceof FloatDataPoint)
        {
            //no output float done yet so no float are ever pushed to the field
        }
    }
    
    /**
     * Stub
     * @param label Label
     * @param value Value
     */
    public void pushToField(String label, boolean value)
    {
      //  System.out.println("Field:" + label + ":" + value);

    }
    
    /**
     * Stub
     * @param label Label
     * @param value Value
     */
    public void pushToField(String label, float value)
    {
        //System.out.println("Field:" +label + ":" + value);
    }
    
    public void poll() {
        //Check modbus connexion
        if (!ModbusConnector.getInstance().connect()) {
            System.out.println("No modbus connection.");
        } else {
            try {
                for (InputRegister inputRegister : inputRegisterHashMap.values()) {
                    inputRegister.request();
                }
                for (Coil coil : coilsHashMap.values()) {
                    //toggle the value each time
                    ((BinaryDataPoint)coil.getDatapoint()).setValue(!((BinaryDataPoint)coil.getDatapoint()).getValue());
                //    coil.send();
                }
                for (DiscreteInput di : discreteInputHashMap.values()) {
                    di.request();
                }
            } catch(NullPointerException e)
            {
                e.printStackTrace();
                System.out.println("Connection ended unexpectedly.");
            }
        }
    }
    
    /**
     * Registers a new input register to the fieldconnector.
     * @param label Label
     * @param rtuAddress RTU Address
     * @param regAddress Register Address
     */
    public void addInputRegister(String label ,int rtuAddress, int regAddress)
    {
        InputRegister ir = new InputRegister(label, rtuAddress, regAddress);
        inputRegisterHashMap.put(ir.getDatapoint(), ir);
    }
    
    /**
     * Registers a new coil to the fieldconnector.
     * @param label Label
     * @param rtuAddress RTU Address
     * @param regAddress Register Address
     */
    public void addCoil(String label ,int rtuAddress, int regAddress)
    {
        Coil c = new Coil(label, rtuAddress, regAddress);
        coilsHashMap.put(c.getDatapoint(), c);
    }
    

    
    /**
     * Registers a new discrete input to the fieldconnector.
     * @param label Label
     * @param rtuAddress RTU Address
     * @param regAddress Register Address
     */
    public void addDiscreteInput(String label, int rtuAddress, int regAddress) {
        DiscreteInput d = new DiscreteInput(label, rtuAddress, regAddress);
        discreteInputHashMap.put(d.getDatapoint(), d);
    }
    
    /**
     * Static Poll Task
     */
    public static class PollTask extends TimerTask {
        @Override
        public void run() {
            getInstance().poll();
        }
    }
}
