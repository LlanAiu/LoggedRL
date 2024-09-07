package org.llan.loggedrl.framework.environment;

public abstract class EpisodicEnvironment extends Environment{

    public void run(){
        if(!_setInitialState){
            throw new IllegalStateException("Initial state not set");
        }

        while(!_currentState.isTerminal()){
            _currentState.periodic();
            if(_currentState.shouldTransition()){
                _currentState.transition();
            }
        }

        onEnd();
    }

    public abstract void onEnd();
}
