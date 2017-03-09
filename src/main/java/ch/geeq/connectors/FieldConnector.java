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

    private static FieldConnector instance;
    final HashMap<DataPoint, InputRegister> inputRegisterHashMap = new HashMap<>();
    final HashMap<DataPoint, Coil> coilsHashMap = new HashMap<>();
    final HashMap<DataPoint, DiscreteInput> discreteInputHashMap = new HashMap<>();
    
    private FieldConnector()
    {
        ModbusConnector.getInstance().connect("localhost", 1502);

        //Schedule a polling of inputs
        Timer t = new Timer();
        t.scheduleAtFixedRate(new FieldConnector.PollTask(), 0, 3000);
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
            pushToField(dp.getLabel(), ((BinaryDataPoint) dp).getValue());
            Coil coil = coilsHashMap.get(dp);

            coil.write();

        }
        else if (dp instanceof FloatDataPoint)
        {
            pushToField(dp.getLabel(), ((FloatDataPoint) dp).getValue());
        }
    }
    
    /**
     * Stub
     * @param label Label
     * @param value Value
     */
    public void pushToField(String label, boolean value)
    {
        System.out.println("Field:" + label + ":" + value);


    }
    
    /**
     * Stub
     * @param label Label
     * @param value Value
     */
    public void pushToField(String label, float value)
    {
        System.out.println("Field:" +label + ":" + value);
    }
    
    public void poll() {
        for(InputRegister inputRegister : inputRegisterHashMap.values())
        {
            inputRegister.read();
        }
        
        for(Coil coil : coilsHashMap.values())
        {
            coil.getDatapoint().setValue(!coil.getDatapoint().getValue());
            coil.write();
        }
        
        for(DiscreteInput di : discreteInputHashMap.values())
        {
            di.read();
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
        inputRegisterHashMap.put(ir.getDataPoint(), ir);
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
    
    static int num = 1;
    
    /**
     * Registers a new discrete input to the fieldconnector.
     * @param label Label
     * @param rtuAddress RTU Address
     * @param regAddress Register Address
     */
    public void addDiscreteInput(String label, int rtuAddress, int regAddress) {
        DiscreteInput d = new DiscreteInput(label, rtuAddress, regAddress);
        discreteInputHashMap.put(d.getDataPoint(), d);
    }
    
    /**
     * Static Poll Task
     */
    public static class PollTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("N° : "+num+++"\n");
            getInstance().poll();
        }
    }
}
