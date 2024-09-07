package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.Environment;

public class TurnAction extends Action {
    private int _column;
    private Player _player;

    public TurnAction(int column, Player player, ConnectFour game){
        super(game);
        _player = player;
        _column = column;
    }

    public int getColumn(){
        return _column;
    }

    @Override
    public void execute() {
        ((ConnectFour) _environment).play(_column, _player);
    }

    @Override
    public Action copy(Environment copyEnvironment) {
        return new TurnAction(_column, _player, (ConnectFour) copyEnvironment);
    }
}
