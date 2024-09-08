package org.llan.loggedrl.framework.iterator;

import org.llan.loggedrl.framework.functions.Function;
import org.llan.loggedrl.framework.logging.RLLogger;
import org.llan.loggedrl.framework.logging.Record;
import org.llan.loggedrl.framework.policies.Policy;
import org.llan.loggedrl.framework.util.Matrix;

public abstract class PolicyIterator {
    public Policy _policy;
    public Function _function;
    private int _perspective = 0;

    public PolicyIterator(Policy policy){
        _policy = policy;
        _function = policy.getValueFunction();
    }

    public PolicyIterator(Policy policy, int perspective){
        _policy = policy;
        _function = policy.getValueFunction();
        _perspective = perspective - 1;
    }

    public void update(Record record){
        Matrix update = getUpdate(record);
        if(update != null){
            RLLogger.getInstance().logGradient(_perspective, update.transpose().norm());
            _function.updateWeights(update);
        }
    }

    public abstract Matrix getUpdate(Record record);
}
