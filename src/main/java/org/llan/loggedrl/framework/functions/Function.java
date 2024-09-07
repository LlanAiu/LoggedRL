package org.llan.loggedrl.framework.functions;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.State;
import org.llan.loggedrl.framework.util.Matrix;

public abstract class Function {
    public Matrix _weights;

    public Function(int length){
        _weights = new Matrix(1, length);
        _weights.fill(-0.2, 0.2);
    }

    public void updateWeights(Matrix update){
        _weights = _weights.plus(update);
    }

    public abstract double evaluate(Action action, State state);

    public abstract double valueOf(State state);
}
