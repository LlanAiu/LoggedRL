package org.llan.loggedrl.framework.environment;

public abstract class Environment {
    public State _currentState;

    public Environment(State initialState) {
        _currentState = initialState;
    }

    public void setState(State newState) {
        _currentState = newState;
    }

    public State getState() {
        return _currentState;
    }

    public abstract State nextState(Action action);
}
