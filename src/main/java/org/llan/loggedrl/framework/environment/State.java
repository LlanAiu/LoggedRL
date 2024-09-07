package org.llan.loggedrl.framework.environment;

public interface State {
    boolean isTerminal();
    Feature getFeature();
}
