package org.llan.loggedrl.framework.policies;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.State;

import java.util.List;

public class EpsilonGreedy extends Policy{
    private double _epsilon;

    public EpsilonGreedy(double epsilon) {
        _epsilon = epsilon;
    }

    @Override
    public Action getAction(List<Action> actions, State state) {
        if (Math.random() < _epsilon) {
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
