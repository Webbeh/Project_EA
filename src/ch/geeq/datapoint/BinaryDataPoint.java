package ch.geeq.datapoint;



/**
 * Created by sylvain.ieri on 24.02.2017.
 */
public class BinaryDataPoint extends DataPoint{

    boolean value;

    public BinaryDataPoint(String label, boolean isOutput) {
        super(label, isOutput);
    }


}
