package ch.geeq.datapoint;

import ch.geeq.datapoint.connectors.DataBaseConnector;
import ch.geeq.datapoint.connectors.FieldConnector;
import ch.geeq.datapoint.connectors.WebConnector;

import java.util.HashMap;

/**
 *   Created by sylvain.ieri, on 24.02.2017.
 */
public abstract class DataPoint {

    private final HashMap<String, DataPoint> dataPointMap = new HashMap<>();
    
    
    private DataBaseConnector dbc;
    private WebConnector wc;
    private FieldConnector fc;



    private  String label;

    private boolean isOutput;

    DataPoint(String label, boolean isOutput){
        this.label = label;
        this.isOutput = isOutput;

        dbc = DataBaseConnector.getInstance();
        wc = WebConnector.getInstance();
        fc = FieldConnector.getInstance();
    }

    public String getLabel() {
        return label;
    }

    protected void push()
    {
        if(isOutput)
        {
            dbc.onNewValue(this);
            wc.onNewValue(this);
            fc.onNewValue(this);
        }
        else
        {
            dbc.onNewValue(this);
            wc.onNewValue(this);

        }
    }
    
    private DataPoint getDataPointFromLabel(String label) {
        return dataPointMap.get(label);
    }
    
    boolean isOutput() {
        return isOutput;
    }
}
