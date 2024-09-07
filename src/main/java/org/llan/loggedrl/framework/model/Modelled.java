package org.llan.loggedrl.framework.model;

import org.llan.loggedrl.framework.environment.State;

public interface Modelled<T> {
    T shallowClone(State state);
}
