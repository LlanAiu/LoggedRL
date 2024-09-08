package org.llan.loggedrl.framework.iterator;

import org.llan.loggedrl.framework.functions.Function;
import org.llan.loggedrl.framework.logging.Record;
import org.llan.loggedrl.framework.policies.Policy;
import org.llan.loggedrl.framework.util.Matrix;

public abstract class PolicyIterator {
    public Policy _policy;
    public Function _function;

    public PolicyIterator(Policy policy){
        _policy = policy;
        _function = policy.getValueFunction();
    }

    public void update(Record record){
        Matrix update = getUpdate(record);
        if(update != null)
            _function.updateWeights(update);
    }

    public abstract Matrix getUpdate(Record record);
}
