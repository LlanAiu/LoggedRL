package org.llan.loggedrl.framework.iterator;

import org.llan.loggedrl.framework.environment.EpisodicEnvironment;

public class EpisodeIterator {
    public EpisodicEnvironment _environment;
    public PolicyIterator[] _policyIterators;

    public void update(int perspective){
        _policyIterators[perspective - 1].update(_environment.getRecord());
    }
}
