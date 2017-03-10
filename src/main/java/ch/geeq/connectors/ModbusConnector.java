package ch.geeq.connectors;


import ch.geeq.modbus.Utility;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;

/**
 * Created by rut on 30/01/17.
 */
public class ModbusConnector {

    // Singleton declaration
    private static ModbusConnector _pInstance = null;

    // Modbus library
    private ModbusMaster _modbus;
    private boolean _isInitialized;
    
    private String host;
    private int port;

    private ModbusConnector() {
        _modbus =  null;
        _isInitialized = false;
    }

    public static ModbusConnector getInstance() {
        if (_pInstance == null) {
            _pInstance = new ModbusConnector();
        }
        return _pInstance;
    }
    public boolean reconnect()
    {
        return host != null && !host.equals("") && connect(host, port);
    }
    /**
     * Connect to the modbus TCP gateway
     * @param host Hostname
     * @param port TCP Port
     * @return True if connection succeeded
     */
    public boolean connect(String host, int port) {
        // Construct modbus TCP parameters.
        IpParameters parameters = new IpParameters();
        this.host =host;
        this.port=port;
        parameters.setHost(host);
        parameters.setPort(port);

        // Create the modbus object.
        _modbus = new ModbusFactory().createTcpMaster(parameters, false);

        // Connect to the slave.
        try {
            _modbus.init();
            _isInitialized = true;
            _modbus.setConnected(true);
        } catch (ModbusInitException e) {
            DEBUG_INFO("connect()", " ModbusInitException: " + e.getLocalizedMessage());
            _isInitialized = false;
        }
        // If we arrive here, all is fine.
        return _isInitialized;
    }
    
    /**
     * Reads a float
     * @param rtuAddress RTU Address
     * @param registerAddress Register address
     * @return The value returned by Modbus
     */
    public Float readFloat(int rtuAddress, int registerAddress) throws NullPointerException {
        if (_isInitialized && _modbus.isConnected()) {
            try {
                //4 : function code
                return (Float) _modbus.getValue(BaseLocator.inputRegister(rtuAddress, registerAddress, DataType.FOUR_BYTE_FLOAT));
//                return (Float) _modbus.getValue( 1, 4, address, DataType.FOUR_BYTE_FLOAT);
            } catch (ModbusTransportException e) {
                DEBUG_INFO("readFloat()", " ModbusTransportException: " + e.getLocalizedMessage());
                _modbus.setConnected(false);
            } catch (ErrorResponseException e) {
                DEBUG_INFO("readFloat()", " ErrorResponseException: " + e.getLocalizedMessage());
                _modbus.setConnected(false);
            }
        }
        throw new NullPointerException("Cannot read float.");
    }
    
    public Boolean readBinary(int rtuAddress, int registerAddress) throws NullPointerException {
        if (_isInitialized && _modbus.isConnected()) {
            try {
                return _modbus.getValue(BaseLocator.coilStatus(rtuAddress, registerAddress));
            } catch (ModbusTransportException e) {
                DEBUG_INFO("writeBinary()", " ModbusTransportException: " + e.getLocalizedMessage());
                _modbus.setConnected(false);
            } catch (ErrorResponseException e) {
                DEBUG_INFO("writeBinary()", " ErrorResponseException: " + e.getLocalizedMessage());
                _modbus.setConnected(false);
            }
        }
        throw new NullPointerException("Cannot read binary.");
    }

    public boolean writeBinary(int rtuAddress, int registerAddress, boolean value) {
        if (_isInitialized && _modbus.isConnected()) {
            try {
                _modbus.setValue(BaseLocator.coilStatus(rtuAddress, registerAddress), value);
//                _modbus.setValue(1, 1, address, DataType.BINARY, value);
                return true;
            } catch (ModbusTransportException e) {
                DEBUG_INFO("writeBinary()", " ModbusTransportException: " + e.getLocalizedMessage());
                _modbus.setConnected(false);
            } catch (ErrorResponseException e) {
                DEBUG_INFO("writeBinary()", " ErrorResponseException: " + e.getLocalizedMessage());
                _modbus.setConnected(false);
            }
        }
        return false;
    }



    // DEBUG System.out
    private void DEBUG_INFO(String method, String msg) {
        Utility.DEBUG( "[modbus::ModbusConnector]", method, msg);
    }
   
    public boolean checkConnect() {
        return _modbus.isConnected() && _modbus.testSlaveNode(1);
    }
    public void error() {
        _isInitialized=false;
    }
}
