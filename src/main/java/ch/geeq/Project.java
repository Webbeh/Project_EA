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
        
        connector.addInputRegister("SOLAR_STRING0_I_FLOAT", 1, 1);
        connector.addCoil("COAL_SW", 1, 209);
        
        connector.poll();
        System.out.println("\n\n");
    
        Timer t = new Timer();
        t.scheduleAtFixedRate(new FieldConnector.PollTask(), 0, 2000);
    }
}
