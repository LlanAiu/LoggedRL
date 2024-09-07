package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.Feature;
import org.llan.loggedrl.framework.environment.State;

import java.util.ArrayList;
import java.util.List;

public class Turn extends State {
    private Player _player;
    private int _turnNumber;

    public Turn(Player player, int turnNumber, ConnectFour game){
        super(game);
        _player = player;
        _turnNumber = turnNumber;
    }

    @Override
    public List<Action> getActions() {
        if(isTerminal()){
            return List.of();
        }
        List<Action> actions = new ArrayList<>(7);
        for(int i = 0; i < 7; i++){
            if(((ConnectFour) _environment).getBoard().canPlace(i)) {
                actions.add(new TurnAction(i, _player, (ConnectFour) _environment));
            }
        }
        return actions;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public Feature getFeature() {
        return null;
    }

    @Override
    public void periodic() {
        Action selected = _player.selectAction(getActions());
        selected.execute();
        setTransition(true);
    }

    @Override
    public void transition() {
        if(((ConnectFour) _environment).getBoard().checkWin(_player.getId()) || _turnNumber == 41){
            _environment.setState(new End((ConnectFour) _environment));
            return;
        }
        _environment.setState(new Turn(
                ((ConnectFour) _environment).getOther(_player),
                _turnNumber + 1,
                (ConnectFour) _environment)
        );
    }
}
