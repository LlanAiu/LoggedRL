package org.llan.loggedrl.framework.environment;

public abstract class Environment {
    State _currentState;
    boolean _setInitialState;

    public Environment(){
        _setInitialState = false;
    }

    public void setInitialState(State initialState) {
        _currentState = initialState;
        _setInitialState = true;
    }

    public State getState() {
        return _currentState;
    }

    public void setState(State state) {
        _currentState = state;
        _setInitialState = true;
    }
}
