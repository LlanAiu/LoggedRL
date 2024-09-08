package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.State;
import org.llan.loggedrl.framework.policies.Policy;

import java.util.List;

public class Player {
    private int _id;
    Policy _policy;

    public Player(int id){
        _id = id;
    }

    public void setPolicy(Policy policy){
        _policy = policy;
    }

    public Policy getPolicy(){
        return _policy;
    }

    public int getId(){
        return _id;
    }

    public Action selectAction(List<Action> moves, State state){
        return _policy.getAction(moves, state);
    }
}
