package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.iterator.EpisodeIterator;

public class CFIterator extends EpisodeIterator {

    public static CFIterator _instance;

    public static CFIterator getInstance(){
        if(_instance == null){
            _instance = new CFIterator();
        }
        return _instance;
    }

    public void setGame(ConnectFour game){
        _environment = game;
        _policyIterators = new CFPolicyIterator[2];
        _policyIterators[0] = new CFPolicyIterator(Constants.PLAYER1, game._player1.getPolicy());
        _policyIterators[1] = new CFPolicyIterator(Constants.PLAYER2, game._player2.getPolicy());
    }
}
