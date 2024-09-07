package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.environment.Action;

public class TurnAction extends Action {
    private int _column;
    private Player _player;

    public TurnAction(int column, Player player, ConnectFour game){
        super(game);
        _player = player;
        _column = column;
    }

    @Override
    public void execute() {
        ((ConnectFour) _environment).play(_column, _player);
    }
}
