package ch.geeq;

import ch.geeq.connectors.FieldConnector;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class Project {
    public static void main(String[] args) {
        FieldConnector fieldConnector = FieldConnector.getInstance();
    
        //Add some input registers, discrete inputs and coils.
        //Label, rtu Address, port

//        connector.addDiscreteInput("COAL_SW", 1, 209);
//        connector.addCoil("COAL_SW", 1, 209);
        fieldConnector.addInputRegister("SOLAR_STRING0_I_FLOAT", 1, 1);
//        fieldConnector.addInputRegister("SOLAR_STRING1_I_FLOAT", 1, 9);
//        fieldConnector.addInputRegister("SOLAR_STRING2_I_FLOAT", 1, 17);
//        fieldConnector.addInputRegister("SOLAR_STRING3_I_FLOAT", 1, 25);
//        fieldConnector.addInputRegister("SOLAR_U_FLOAT", 1, 33);
//        fieldConnector.addInputRegister("SOLAR_P_FLOAT", 1, 41);
//        fieldConnector.addInputRegister("BATT_CHRG_FLOAT", 1, 49);
//        fieldConnector.addInputRegister("BATT_P_FLOAT", 1, 57);
//        fieldConnector.addInputRegister("COAL_T_FLOAT", 1, 65);
//        fieldConnector.addInputRegister("COAL_U_FLOAT", 1, 73);
//        fieldConnector.addInputRegister("COAL_P_FLOAT", 1, 81);
//        fieldConnector.addInputRegister("GRID_U_FLOAT", 1, 89);
//        fieldConnector.addInputRegister("GRID_P_FLOAT", 1, 97);
//        fieldConnector.addInputRegister("BAR_U_FLOAT", 1, 105);
//
//        fieldConnector.addDiscreteInput("SOLAR_SW", 1, 201);
//        fieldConnector.addDiscreteInput("COAL_SW", 1, 209);
//        fieldConnector.addDiscreteInput("BATT_SW", 1, 217);
//        fieldConnector.addDiscreteInput("GRID_SW", 1, 225);
//
//        fieldConnector.addCoil("SOLAR_SW", 1, 201);
//        fieldConnector.addCoil("COAL_SW", 1, 209);
//        fieldConnector.addCoil("BATT_SW", 1, 217);
//        fieldConnector.addCoil("GRID_SW", 1, 225);

    }
}
