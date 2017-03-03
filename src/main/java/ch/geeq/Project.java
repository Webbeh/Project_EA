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

//        connector.addDiscreteInput("COAL_SW", 1, 209);
//        connector.addCoil("COAL_SW", 1, 209);
        connector.addInputRegister("SOLAR_STRING0_I_FLOAT", 1, 1);
        connector.addInputRegister("SOLAR_STRING1_I_FLOAT", 1, 9);
        connector.addInputRegister("SOLAR_STRING2_I_FLOAT", 1, 17);
        connector.addInputRegister("SOLAR_STRING3_I_FLOAT", 1, 25);
        connector.addInputRegister("SOLAR_U_FLOAT", 1, 33);
        connector.addInputRegister("SOLAR_P_FLOAT", 1, 41);
        connector.addInputRegister("BATT_CHRG_FLOAT", 1, 49);
        connector.addInputRegister("BATT_P_FLOAT", 1, 57);
        connector.addInputRegister("COAL_T_FLOAT", 1, 65);
        connector.addInputRegister("COAL_U_FLOAT", 1, 73);
        connector.addInputRegister("COAL_P_FLOAT", 1, 81);
        connector.addInputRegister("GRID_U_FLOAT", 1, 89);
        connector.addInputRegister("GRID_P_FLOAT", 1, 97);
        connector.addInputRegister("BAR_U_FLOAT", 1, 105);
    
        connector.addDiscreteInput("SOLAR_SW", 1, 201);
        connector.addDiscreteInput("COAL_SW", 1, 209);
        connector.addDiscreteInput("BATT_SW", 1, 217);
        connector.addDiscreteInput("GRID_SW", 1, 225);
        connector.addCoil("SOLAR_SW", 1, 201);
        connector.addCoil("COAL_SW", 1, 209);
        connector.addCoil("BATT_SW", 1, 217);
        connector.addCoil("GRID_SW", 1, 225);
    
        //Schedule a polling of inputs
        Timer t = new Timer();
        t.scheduleAtFixedRate(new FieldConnector.PollTask(), 0, 3000);
    }
}
