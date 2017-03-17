import ch.geeq.connectors.ModbusConnector;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.0.0
 * @since 3/17/17
 */
public class SocketTest {
    public static void main(String[] args) {
        try {
        while(true) {
            ModbusConnector mc = ModbusConnector.getInstance();
            System.out.println("Connect : "+mc.connect("127.0.0.1", 1502));
            Thread.sleep(1000);
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
