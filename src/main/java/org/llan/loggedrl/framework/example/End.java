package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.Environment;
import org.llan.loggedrl.framework.environment.Feature;
import org.llan.loggedrl.framework.environment.State;

import java.util.List;

public class End extends State {
    private Player _nextPlayer;

    public End(ConnectFour game, Player nextPlayer){
        super(game);
        _nextPlayer = nextPlayer;
    }

    public int getOtherPlayerId() {
        return _nextPlayer.getId();
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

    @Override
    public State copy(Environment copyEnvironment) {
        return new End((ConnectFour) copyEnvironment, _nextPlayer);
    }
}
