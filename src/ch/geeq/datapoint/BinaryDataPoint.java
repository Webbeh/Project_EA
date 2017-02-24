package ch.geeq.datapoint;



/**
 * Created by sylvain.ieri on 24.02.2017.
 */
public class BinaryDataPoint extends DataPoint{

    private boolean value;

    public BinaryDataPoint(String label, boolean isOutput) {
        super(label, isOutput);
    }


    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;



    }
    
    public String toString()
    {
        return "BinaryDataPoint. Is output : "+super.isOutput()+". Value : "+value;
    }
}
