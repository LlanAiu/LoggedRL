package org.llan.loggedrl.framework.policies;

import org.llan.loggedrl.framework.environment.Action;

import java.util.List;

public class RandomPolicy extends Policy{

    @Override
    public Action getAction(List<Action> actions) {
        return actions.get((int)(Math.random() * actions.size()));
    }
}
