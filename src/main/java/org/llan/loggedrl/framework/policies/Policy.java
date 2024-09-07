package org.llan.loggedrl.framework.policies;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.functions.Function;

public abstract class Policy {
    private Function _valueFunction;

    public abstract Action getAction();
}
