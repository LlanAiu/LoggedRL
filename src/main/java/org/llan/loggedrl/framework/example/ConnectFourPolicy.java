package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.policies.EpsilonGreedy;

public class ConnectFourPolicy extends EpsilonGreedy {

    public ConnectFourPolicy(double epsilon, Player player, ConnectFour game) {
        super(epsilon);
        setValueFunction(new ConnectFourFunction(player, game));
    }
}
