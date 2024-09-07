package org.llan.loggedrl.framework.logging;

import org.llan.loggedrl.framework.environment.Action;
import org.llan.loggedrl.framework.environment.State;

public class TimeStep {
    private int _timeStep;
    private double _reward;
    private State _state;
    private Action _action;

    public TimeStep(int timeStep, State state) {
        _timeStep = timeStep;
        _state = state;
    }

    public TimeStep(int timeStep, double reward, State state, Action action) {
        _timeStep = timeStep;
        _reward = reward;
        _state = state;
        _action = action;
    }

    public Action getAction() {
        return _action;
    }

    public double getReward() {
        return _reward;
    }

    public State getState() {
        return _state;
    }

    public int getTimeStep() {
        return _timeStep;
    }

    public void setAction(Action _action) {
        this._action = _action;
    }

    public void setReward(double _reward) {
        this._reward = _reward;
    }

    public void setState(State _state) {
        this._state = _state;
    }

    public void setTimeStep(int _timeStep) {
        this._timeStep = _timeStep;
    }
}
