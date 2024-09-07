package org.llan.loggedrl.framework.environment;

import org.llan.loggedrl.framework.util.Matrix;

public class Feature {
    Matrix _features;

    public Feature(int length){
        _features = new Matrix(1, length);
    }

    public void setValue(int index, double value){
        _features.set(0, index, value);
    }

    public double getValue(int index){
        return _features.get(0, index);
    }

    public Matrix getMatrix(){
        return _features;
    }
}
