package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.Feature;
import org.llan.loggedrl.framework.environment.State;

import java.util.List;

public class End extends State {

    public End(ConnectFour game){
        super(game);
    }

    @Override
    public List<Action> getActions() {
        return List.of();
    }

    @Override
    public boolean isTerminal() {
        return true;
    }

    @Override
    public Feature getFeature() {
        return null;
    }

    @Override
    public void periodic() {
        throw new UnsupportedOperationException("Terminal state can not be run");
    }

    @Override
    public boolean shouldTransition() {
        return false;
    }

    @Override
    public void transition() {
        throw new UnsupportedOperationException("Terminal state can not be run");
    }
}
