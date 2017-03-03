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

    public boolean connect(String host, int port) {
        // Construct modbus TCP parameters.
        IpParameters parameters = new IpParameters();
        parameters.setHost(host);
        parameters.setPort(port);

        // Create the modbus object.
        _modbus = new ModbusFactory().createTcpMaster(parameters, false);

        // Connect to the slave.
        try {
            _modbus.init();
            _isInitialized = true;
        } catch (ModbusInitException e) {
            DEBUG_INFO("connect()", " ModbusInitException: " + e.getLocalizedMessage());
            _isInitialized = false;
        }

        // If we arrive here, all is fine.
        return _isInitialized;
    }

    public Float readFloat(int rtuAddress, int registerAddress) {
        if (_isInitialized) {
            try {
                //4 : function code
                return (Float) _modbus.getValue(BaseLocator.inputRegister(rtuAddress, registerAddress, DataType.FOUR_BYTE_FLOAT));
//                return (Float) _modbus.getValue( 1, 4, address, DataType.FOUR_BYTE_FLOAT);
            } catch (ModbusTransportException e) {
                DEBUG_INFO("readFloat()", " ModbusTransportException: " + e.getLocalizedMessage());
            } catch (ErrorResponseException e) {
                DEBUG_INFO("readFloat()", " ErrorResponseException: " + e.getLocalizedMessage());
            }
        }
        return null;
    }

    public boolean readBinary(int rtuAddress, int registerAddress) {
        if (_isInitialized) {
            try {
                return _modbus.getValue(BaseLocator.coilStatus(rtuAddress, registerAddress));
            } catch (ModbusTransportException e) {
                DEBUG_INFO("writeBinary()", " ModbusTransportException: " + e.getLocalizedMessage());
            } catch (ErrorResponseException e) {
                DEBUG_INFO("writeBinary()", " ErrorResponseException: " + e.getLocalizedMessage());
            }
        }
        return false;
    }

    public boolean writeBinary(int rtuAddress, int registerAddress, boolean value) {
        if (_isInitialized) {
            try {
                _modbus.setValue(BaseLocator.coilStatus(rtuAddress, registerAddress), value);
//                _modbus.setValue(1, 1, address, DataType.BINARY, value);
                return true;
            } catch (ModbusTransportException e) {
                DEBUG_INFO("writeBinary()", " ModbusTransportException: " + e.getLocalizedMessage());
            } catch (ErrorResponseException e) {
                DEBUG_INFO("writeBinary()", " ErrorResponseException: " + e.getLocalizedMessage());
            }
        }
        return false;
    }



    // DEBUG System.out
    private void DEBUG_INFO(String method, String msg) {
        Utility.DEBUG( "[modbus::ModbusConnector]", method, msg);
    }
}
