package org.llan.loggedrl.framework.logging;

import org.llan.loggedrl.framework.example.Constants;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private List<TimeStep>[] _perspectives;
    private double[] _rewards;

    public Record(int numPerspectives){
        _perspectives = new List[numPerspectives];
        _rewards = new double[numPerspectives];
        for(int i = 0; i < numPerspectives; i++){
            _perspectives[i] = new ArrayList<>();
        }
    }

    public void addTimeStep(int perspective, TimeStep timeStep){
        _perspectives[perspective - 1].add(timeStep);
        _rewards[perspective - 1] += timeStep.getReward();
    }

    public void endOfEpisode(int winningPerspective){
        for(int i = 0; i < _perspectives.length; i++){
            if(winningPerspective == 0){
                _rewards[i] = Constants.DRAW;
            } else {
                if((i + 1) == winningPerspective){
                    _rewards[i] = Constants.WIN;
                } else {
                    _rewards[i] = Constants.LOSS;
                }
            }
        }
        for(int i = 0; i < _perspectives.length; i++){
            RLLogger.getInstance().logReward(i, _rewards[i]);
            RLLogger.getInstance().averageGradient(i);
        }

    }

    public TimeStep getLast(int perspective) {
        return _perspectives[perspective - 1].get(_perspectives[perspective - 1].size() - 1);
    }

    public List<TimeStep> getLast(int perspective, int num){
        return _perspectives[perspective - 1].subList(_perspectives[perspective - 1].size() - num, _perspectives[perspective - 1].size());
    }

    public int size(int perspective){
        return _perspectives[perspective - 1].size();
    }

}
