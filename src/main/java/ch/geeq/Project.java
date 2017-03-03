package ch.geeq;

import ch.geeq.connectors.FieldConnector;
import ch.geeq.connectors.WebConnector;

import java.util.Timer;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 2/24/17
 */
public class Project {
    public static void main(String[] args) {
        FieldConnector connector = FieldConnector.getInstance();
        connector.addCoil("Coil 1", 1, 3);
        connector.addCoil("Coil 2", 3, 7);
        
        connector.addInputRegister("Input 1", 1, 4);
        connector.addInputRegister("Input 2", 5, 1);
        
        connector.poll();
        System.out.println("\n\n");
    
        WebConnector.getInstance().onMessage("Coil 2", false);
        Timer t = new Timer();
        t.scheduleAtFixedRate(new FieldConnector.PollTask(), 0, 2000);
    }
}
