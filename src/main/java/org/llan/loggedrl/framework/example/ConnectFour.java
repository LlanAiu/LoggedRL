package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.environment.EpisodicEnvironment;

public class ConnectFour extends EpisodicEnvironment {
    Player player1;
    Player player2;
    Board board;

    public ConnectFour(){
        player1 = new Player(1);
        player2 = new Player(-1);
        board = new Board();
        setInitialState(new Turn(player1, 0, this));
    }

    public Board getBoard(){
        return board;
    }

    public Player getOther(Player player){
        return player == player1 ? player2 : player1;
    }

    public void play(int column, Player player){
        board.place(column, player.getId());
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
}
