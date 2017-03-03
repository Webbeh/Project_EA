package ch.geeq.datapoint;

import ch.geeq.connectors.DataBaseConnector;
import ch.geeq.connectors.FieldConnector;
import ch.geeq.connectors.WebConnector;

import java.util.HashMap;

/**
 *   Created by sylvain.ieri, on 24.02.2017.
 */
public abstract class DataPoint {

    private final static HashMap<String, DataPoint> dataPointMap = new HashMap<>();
    
    
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

        dataPointMap.put(label, this);

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
    
    public static DataPoint getDataPointFromLabel(String label) {
        return dataPointMap.get(label);
    }
    
    boolean isOutput() {
        return isOutput;
    }
}