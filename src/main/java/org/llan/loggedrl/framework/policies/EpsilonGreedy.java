package org.llan.loggedrl.framework.policies;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.State;
import org.llan.loggedrl.framework.logging.RLLogger;

import java.util.List;

public class EpsilonGreedy extends Policy{
    private double _epsilon;
    private double _epsilonDecay = 0.99;

    public EpsilonGreedy(double epsilon) {
        _epsilon = epsilon;
    }

    public EpsilonGreedy(double epsilon, int perspective) {
        _epsilon = epsilon;
        _perspective = perspective;
    }

    public double getEpsilon() {
        return _epsilon;
    }

    public void setEpsilon(double epsilon) {
        _epsilon = epsilon;
    }

    public void setEpsilonDecay(double epsilonDecay) {
        _epsilonDecay = epsilonDecay;
    }

    @Override
    public Action getAction(List<Action> actions, State state) {
        RLLogger.getInstance().recordDouble(_perspective, "epsilon", _epsilon);
        if (Math.random() < _epsilon) {
            _epsilon *= _epsilonDecay;
            return actions.get((int)(Math.random() * actions.size()));
        } else {
            Action bestAction = actions.get(0);
            double max = _valueFunction.evaluate(bestAction, state);
            for(int i = 1; i < actions.size(); i++){
                Action action = actions.get(i);
                double value = _valueFunction.evaluate(action, state);
                if(value > max){
                    max = value;
                    bestAction = action;
                }
            }
            return bestAction;
        }
    }
}
