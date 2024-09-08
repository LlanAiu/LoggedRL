package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.environment.EpisodicEnvironment;
import org.llan.loggedrl.framework.environment.State;
import org.llan.loggedrl.framework.logging.Record;
import org.llan.loggedrl.framework.logging.TimeStep;
import org.llan.loggedrl.framework.model.Modelled;

public class ConnectFour extends EpisodicEnvironment implements Modelled<ConnectFour> {
    Player _player1;
    Player _player2;
    Board _board;
    Record _record;

    public ConnectFour(){
        _player1 = new Player(Constants.PLAYER1);
        _player2 = new Player(Constants.PLAYER2);
        _player1.setPolicy(new ConnectFourPolicy(0.8, _player1, this));
        _player2.setPolicy(new ConnectFourPolicy(0.8, _player2, this));
        _board = new Board();
        setInitialState(new Turn(_player1, 0, this));
        _record = new Record(2);
    }

    public ConnectFour(Player player1, Player player2, Board board){
        this._player1 = player1;
        this._player2 = player2;
        this._board = board;
    }

    public Board getBoard(){
        return _board;
    }

    public Player getOther(Player player){
        return player == _player1 ? _player2 : _player1;
    }

    public void play(int column, Player player){
        _board.place(column, player.getId());
        nextTurn();
    }

    public void nextTurn() {
        getState().queueTransition();
    }

    public int getWinner(){
        if(_board.checkWin(_player1.getId())){
            return _player1.getId();
        } else if(_board.checkWin(_player2.getId())){
            return _player2.getId();
        } else {
            return 0;
        }
    }

    public void record(int player, TimeStep step){
        _record.addTimeStep(player, step);
    }

    @Override
    public Record getRecord(){
        return _record;
    }

    @Override
    public double getReward(int perspective) {
        if(getState().isTerminal()){
            int winningIndex = getWinner();
            if(winningIndex == 0){
                return Constants.DRAW;
            } else {
                return winningIndex == perspective ? Constants.WIN : -Constants.LOSS;
            }
        }
        return 0;
    }

    public void onEnd(){
        _record.endOfEpisode(getWinner());
        CFIterator.getInstance().update(((End) getState()).getOtherPlayerId());
        if(_board.checkWin(_player1.getId())){
            System.out.println("Player 1 wins");
        } else if(_board.checkWin(_player2.getId())){
            System.out.println("Player 2 wins");
        } else {
            System.out.println("Draw");
        }
        saveAll();
    }

    public void saveAll(){
        _player1.getPolicy().save("p1_data.txt");
        _player2.getPolicy().save("p2_data.txt");
    }

    @Override
    public ConnectFour shallowClone(State state) {
        ConnectFour copy = new ConnectFour(_player1, _player2, _board.copy());
        copy.setState(state.copy(copy));
        return copy;
    }
}
