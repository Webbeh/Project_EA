package ch.geeq.datapoint;

import ch.geeq.datapoint.connectors.DataBaseConnector;
import ch.geeq.datapoint.connectors.FieldConnector;
import ch.geeq.datapoint.connectors.WebConnector;

/**
 * Created by sylvain.ieri on 24.02.2017.
 */
public class DataPoint {

    private DataBaseConnector dbc;
    private WebConnector wc;
    private FieldConnector fc;


    private  String label;

    private boolean isOutput;

    public DataPoint(String label, boolean isOutput){
        this.label = label;
        this.isOutput = isOutput;

        //TODO: get the instaces of connectors
    }

    public String getLabel() {
        return label;
    }

}
