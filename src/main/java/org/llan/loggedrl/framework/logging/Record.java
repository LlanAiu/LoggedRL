package org.llan.loggedrl.framework.logging;

import java.util.List;

public class Record {
    private List<TimeStep>[] _perspectives;
    private double[] rewards;

    public Record(int numPerspectives){
        _perspectives = new List[numPerspectives];
        rewards = new double[numPerspectives];
    }

    public void addTimeStep(int perspective, TimeStep timeStep){
        _perspectives[perspective].add(timeStep);
        rewards[perspective] += timeStep.getReward();
    }

    public void endOfEpisode(int winningPerspective){
        for(int i = 0; i < _perspectives.length; i++){
            if(i == winningPerspective){
                rewards[i] = 1;
            } else {
                rewards[i] = 0;
            }
        }
    }

}
