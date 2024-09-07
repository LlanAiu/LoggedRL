package org.llan.loggedrl.framework.environment;

public abstract class Feature {
    double[] _features;

    public Feature(int length){
        _features = new double[length];
    }
}
