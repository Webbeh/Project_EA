package ch.geeq.modbus;


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

    public Float readFloat(int address) {
        if (_isInitialized) {
            try {
                return (Float) _modbus.getValue( 1, 4, address, DataType.FOUR_BYTE_FLOAT);
            } catch (ModbusTransportException e) {
                DEBUG_INFO("readFloat()", " ModbusTransportException: " + e.getLocalizedMessage());
            } catch (ErrorResponseException e) {
                DEBUG_INFO("readFloat()", " ErrorResponseException: " + e.getLocalizedMessage());
            }
        }
        return null;
    }

    public boolean readBinary(int address) {
        if (_isInitialized) {
            try {
                return (boolean) _modbus.getValue(1, 1, address, DataType.BINARY);
            } catch (ModbusTransportException e) {
                DEBUG_INFO("writeBinary()", " ModbusTransportException: " + e.getLocalizedMessage());
            } catch (ErrorResponseException e) {
                DEBUG_INFO("writeBinary()", " ErrorResponseException: " + e.getLocalizedMessage());
            }
        }
        return false;
    }

    public boolean writeBinary(int address, boolean value) {
        if (_isInitialized) {
            try {
                _modbus.setValue(1, 1, address, DataType.BINARY, value);
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
