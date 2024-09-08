package org.llan.loggedrl.framework;

import org.llan.loggedrl.framework.example.CFIterator;
import org.llan.loggedrl.framework.example.ConnectFour;
import org.llan.loggedrl.framework.logging.RLLogger;

public class Main {

    public static void main(String[] args) {
        RLLogger.getInstance().setSize(2);

        for(int i = 0; i < 300; i++){
            ConnectFour game = new ConnectFour();
            CFIterator.getInstance().setGame(game);
            game.run();
        }

        RLLogger.getInstance().writeLog();
    }

}
