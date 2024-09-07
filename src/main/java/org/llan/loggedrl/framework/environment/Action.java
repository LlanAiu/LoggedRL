package org.llan.loggedrl.framework.environment;

public abstract class Action {
    public Environment _environment;

    public Action(Environment environment){
        this._environment = environment;
    }

    public abstract void execute();
}
