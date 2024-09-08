package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.functions.ModelFunction;

public class ConnectFourFunction extends ModelFunction {

    public ConnectFourFunction(Player player, ConnectFour game) {
        super(Constants.FEATURE_LENGTH, new ConnectFourModel(player, game));
        this.load("p" + player.getId() + "_data.txt");
    }
}
