package org.llan.loggedrl.framework.functions;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.State;
import org.llan.loggedrl.framework.save.DataSaver;
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

    public void load(String filename){
        Matrix matrix = DataSaver.loadMatrixFromFile(filename);
        if(matrix != null && matrix.getRows() == _weights.getRows() && matrix.getCols() == _weights.getCols()){
            _weights = matrix.transpose();
        }
    }

    public void save(String filename){
        DataSaver.saveMatrixToFile(_weights.transpose(), filename);
    }

    public abstract double evaluate(Action action, State state);

    public abstract double valueOf(State state);
}
