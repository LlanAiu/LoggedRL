package org.llan.loggedrl.framework.environment;

import java.util.List;

public abstract class State {
    public Environment _environment;
    public boolean _shouldTransition = false;

    public State(Environment environment){
        _environment = environment;
    }

    public Environment getEnvironment(){
        return _environment;
    }

    public void queueTransition(){
        _shouldTransition = true;
    }

    public boolean shouldTransition() {
        return _shouldTransition;
    }

    public abstract boolean isTerminal();
    public abstract Feature getFeature();
    public abstract List<Action> getActions();
    public abstract void periodic();
    public abstract void transition();
    public abstract State copy(Environment copyEnvironment);
}
