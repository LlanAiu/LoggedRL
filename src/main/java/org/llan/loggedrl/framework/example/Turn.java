package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.Environment;
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

    public int getPlayerId(){
        return _player.getId();
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public Feature getFeature() {
        Feature feature = new Feature(Constants.FEATURE_LENGTH);
        for(int p = 0; p < 2; p++){
            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 7; j++){
                    if(p == 0){
                        feature.setValue(i * 7 + j, ((ConnectFour) _environment).getBoard().getValue(i, j) == _player.getId() ? 1 : 0);
                    } else {
                        feature.setValue(i * 7 + j + 42, ((ConnectFour) _environment).getBoard().getValue(i, j) == -_player.getId() ? 1 : 0);
                    }
                }
            }
        }
        return feature;
    }

    @Override
    public void periodic() {
        Action selected = _player.selectAction(getActions(), this);
        selected.execute();
        System.out.println("Player " + _player.getId() + " played " + ((TurnAction) selected).getColumn());
    }



    @Override
    public void transition() {
        System.out.println("Board: \n" + ((ConnectFour) _environment).getBoard().toString());
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

    @Override
    public State copy(Environment copyEnvironment) {
        return new Turn(_player, _turnNumber, (ConnectFour) copyEnvironment);
    }
}
