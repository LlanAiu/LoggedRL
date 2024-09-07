package org.llan.loggedrl.framework.model;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.State;

public interface Model {
    State getNextState(State state, Action action);
    double getReward(State state, Action action);
}
