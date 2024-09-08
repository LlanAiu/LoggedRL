package org.llan.loggedrl.framework;

import org.llan.loggedrl.framework.example.CFIterator;
import org.llan.loggedrl.framework.example.ConnectFour;

public class Main {

    public static void main(String[] args) {

        for(int i = 0; i < 100; i++){
            ConnectFour game = new ConnectFour();
            CFIterator.getInstance().setGame(game);
            game.run();
        }

    }

}
