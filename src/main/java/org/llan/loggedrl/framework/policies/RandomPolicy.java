package org.llan.loggedrl.framework.policies;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.State;

import java.util.List;

public class RandomPolicy extends Policy{

    @Override
    public Action getAction(List<Action> actions, State state) {
        return actions.get((int)(Math.random() * actions.size()));
    }
}
