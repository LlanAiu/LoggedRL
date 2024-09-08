package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.iterator.PolicyIterator;
import org.llan.loggedrl.framework.logging.Record;
import org.llan.loggedrl.framework.logging.TimeStep;
import org.llan.loggedrl.framework.policies.Policy;
import org.llan.loggedrl.framework.util.Matrix;

import java.util.List;

public class CFPolicyIterator extends PolicyIterator {
    private int _playerId;

    public CFPolicyIterator(int playerId, Policy policy){
        super(policy, playerId);
        _playerId = playerId;
    }

    @Override
    public Matrix getUpdate(Record record) {
        if(record.size(_playerId) < 2){
            return null;
        }
        List<TimeStep> moves = record.getLast(_playerId, 2);
        TimeStep last = moves.getLast();
        TimeStep prior = moves.getFirst();
        double value = AIConstants.STEP_SIZE * (prior.getReward()
                + AIConstants.DISCOUNT * (_function.evaluate(last.getAction(), last.getState()))
                - _function.evaluate(prior.getAction(), prior.getState()));

        return prior.getState().getFeature().getMatrix().scale(value);
    }
}
