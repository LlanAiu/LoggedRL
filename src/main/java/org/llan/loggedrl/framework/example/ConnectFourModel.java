package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.State;
import org.llan.loggedrl.framework.model.Model;

public class ConnectFourModel implements Model {

    Player _player;
    ConnectFour _game;

    public ConnectFourModel(Player player, ConnectFour game){
        _player = player;
        _game = game;
    }

    @Override
    public State getNextState(State state, Action action) {
        ConnectFour copy = _game.shallowClone(state);
        Action copyAction = action.copy(copy);
        copyAction.execute();
        return copy.getState();
    }

    @Override
    public double getReward(State state, Action action) {
        if(state.isTerminal()){
            int winningIndex = ((End) state).getWinner();
            if(winningIndex == 0){
                return Constants.DRAW;
            } else {
                return winningIndex == _player.getId() ? Constants.WIN : -Constants.LOSS;
            }
        }
        return 0;
    }
}
