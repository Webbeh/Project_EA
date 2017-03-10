package ch.geeq.datapoint;

/**
 * Created by sylvain.ieri on 24.02.2017.
 */
public class FloatDataPoint extends DataPoint {
    private Float value;

    public FloatDataPoint(String label, boolean isOutput) {
        super(label, isOutput);
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;

        push();
    }
    
    public String toString()
    {
        return "FloatDataPoint. Is output : "+super.isOutput()+". Value : "+value;
    }
}
