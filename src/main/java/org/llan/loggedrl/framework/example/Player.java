package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.policies.Policy;
import org.llan.loggedrl.framework.policies.RandomPolicy;

import java.util.List;

public class Player {
    private int _id;
    Policy _policy;

    public Player(int id){
        _id = id;
        _policy = new RandomPolicy();
    }

    public int getId(){
        return _id;
    }

    public Action selectAction(List<Action> moves){
        return _policy.getAction(moves);
    }
}
