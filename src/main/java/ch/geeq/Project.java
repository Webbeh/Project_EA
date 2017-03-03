package ch.geeq;

import ch.geeq.connectors.FieldConnector;

import java.util.Timer;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class Project {
    public static void main(String[] args) {
        FieldConnector connector = FieldConnector.getInstance();
       
        //Add some input registers, discrete inputs and coils.
        //Label, rtu Address, port
        connector.addInputRegister("BATT_P_FLOAT", 1, 57);
        connector.addInputRegister("BATT_CHRG_FLOAT", 1, 49);
        connector.addInputRegister("COAL_P_FLOAT", 1, 81);
        connector.addInputRegister("SOLAR_STRING0_I_FLOAT", 1, 1);
//        connector.addDiscreteInput("COAL_SW", 1, 209);
//        connector.addCoil("COAL_SW", 1, 209);
      
        //Schedule a polling of inputs
        Timer t = new Timer();
        t.scheduleAtFixedRate(new FieldConnector.PollTask(), 0, 3000);
    }
}
