package org.llan.loggedrl.framework.policies;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.State;
import org.llan.loggedrl.framework.functions.Function;

import java.util.List;

public abstract class Policy {
    Function _valueFunction;

    public void setValueFunction(Function valueFunction) {
        this._valueFunction = valueFunction;
    }

    public Function getValueFunction(){
        return _valueFunction;
    }

    public void save(String path){
        _valueFunction.save(path);
        saveParams();
    }

    public void saveParams() {

    }
    public void loadParams() {

    }
    public abstract Action getAction(List<Action> actions, State state);
}
