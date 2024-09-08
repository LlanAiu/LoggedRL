package org.llan.loggedrl.framework.functions;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.State;
import org.llan.loggedrl.framework.model.Model;

public class ModelFunction extends Function {
    Model _model;

    public ModelFunction(int length, Model model) {
        super(length);
        _model = model;
    }

    @Override
    public double evaluate(Action action, State state) {
        return valueOf(_model.getNextState(state, action));
    }

    @Override
    public double valueOf(State state) {
        if(state.isTerminal()){
            return 0.0;
        }
        return state.getFeature().getMatrix().multiply(_weights.transpose()).get(0, 0);
    }
}
