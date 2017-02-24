package ch.geeq.datapoint;

/**
 * Created by sylvain.ieri on 24.02.2017.
 */
public class FloatDataPoint extends DataPoint {
    private float value;

    public FloatDataPoint(String label, boolean isOutput) {
        super(label, isOutput);
    }


    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
