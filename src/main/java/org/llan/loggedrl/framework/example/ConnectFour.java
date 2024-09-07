package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.environment.EpisodicEnvironment;
import org.llan.loggedrl.framework.environment.State;
import org.llan.loggedrl.framework.model.Modelled;

public class ConnectFour extends EpisodicEnvironment implements Modelled<ConnectFour> {
    Player player1;
    Player player2;
    Board board;

    public ConnectFour(){
        player1 = new Player(Constants.PLAYER1);
        player2 = new Player(Constants.PLAYER2);
        player1.setPolicy(new ConnectFourPolicy(0.05, player1, this));
        player2.setPolicy(new ConnectFourPolicy(0.05, player2, this));
        board = new Board();
        setInitialState(new Turn(player1, 0, this));
    }

    public ConnectFour(Player player1, Player player2, Board board){
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
    }

    public Board getBoard(){
        return board;
    }

    public Player getOther(Player player){
        return player == player1 ? player2 : player1;
    }

    public void play(int column, Player player){
        board.place(column, player.getId());
        nextTurn();
    }

    public void nextTurn() {
        getState().queueTransition();
    }

    public int getWinner(){
        if(board.checkWin(player1.getId())){
            return player1.getId();
        } else if(board.checkWin(player2.getId())){
            return player2.getId();
        } else {
            return 0;
        }
    }

    public void onEnd(){
        if(board.checkWin(player1.getId())){
            System.out.println("Player 1 wins");
        } else if(board.checkWin(player2.getId())){
            System.out.println("Player 2 wins");
        } else {
            System.out.println("Draw");
        }
    }

    @Override
    public ConnectFour shallowClone(State state) {
        ConnectFour copy = new ConnectFour(player1, player2, board.copy());
        copy.setState(state.copy(copy));
        return copy;
    }
}
