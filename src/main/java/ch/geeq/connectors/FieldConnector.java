package ch.geeq.connectors;

import ch.geeq.datapoint.BinaryDataPoint;
import ch.geeq.datapoint.DataPoint;
import ch.geeq.datapoint.FloatDataPoint;
import ch.geeq.modbus.Coil;
import ch.geeq.modbus.InputRegister;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class FieldConnector {

    private static FieldConnector instance;
    final List<InputRegister> inputRegistersList = new LinkedList<>();
    final HashMap<DataPoint, Coil> coilsHashMap = new HashMap<>();
    
    private FieldConnector()
    {
        ModbusConnector.getInstance().connect("localhost", 1502);
    }
    
    public static FieldConnector getInstance(){

        if(instance == null)
        {
            instance = new FieldConnector();
        }
        return instance;
    }
    
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
    
    public void pushToField(String label, boolean value)
    {
        System.out.println("Field:" + label + ":" + value);


    }
    
    public void pushToField(String label, float value)
    {
        System.out.println("Field:" +label + ":" + value);
    }
    
    public void poll() {
        for(InputRegister inputRegister : inputRegistersList)
        {
            inputRegister.read();
        }
        
        for(Coil coil : coilsHashMap.values())
        {
            coil.getBinaryDatapoint().setValue(!coil.getBinaryDatapoint().getValue());
            coil.write();
        }
    }
    
    public void addInputRegister(String label ,int rtuAddress, int regAddress)
    {
        InputRegister ir = new InputRegister(label, rtuAddress, regAddress);
        inputRegistersList.add(ir);
    }
    
    public void addCoil(String label ,int rtuAddress, int regAddress)
    {
        Coil c = new Coil(label, rtuAddress, regAddress);
        coilsHashMap.put(c.getBinaryDatapoint(), c);
    }
    
    static int num = 1;
    public static class PollTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("N° : "+num+++"\n");
            getInstance().poll();
        }
    }
}
