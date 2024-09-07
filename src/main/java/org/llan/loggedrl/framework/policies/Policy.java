package org.llan.loggedrl.framework.policies;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.functions.Function;

import java.util.List;

public abstract class Policy {
    private Function _valueFunction;

    public abstract Action getAction(List<Action> actions);
}
