package ch.geeq.modbus;

import ch.geeq.connectors.ModbusConnector;
import ch.geeq.datapoint.DataPoint;

/**
 * Created by sieri on 3/17/2017.
 */
public abstract class ModbusReg {

    protected int regAddress, rtuAddress;
    protected DataPoint itsDataPoint;
    protected String label;

    ModbusReg(String label, int rtuAddress, int regAddress)
    {
        this.rtuAddress=rtuAddress;
        this.regAddress=regAddress;
        this.label = label;
    }

    public void request()
    {
        ModbusConnector.getInstance().sendTransaction(this);
    }

    abstract public void recieve();

    abstract public byte[] getPDU();

    public DataPoint getDatapoint() {
        return itsDataPoint;
    }

    public int getRtuAddress()
    {
        return rtuAddress;
    }
}
