package org.llan.loggedrl.framework.logging;

import java.util.ArrayList;
import java.util.List;

public class Logs {
    private List<Double> _gradientNorms;
    private List<Double> _rewards;
    private List<Double> _averageRewards;

    private int _perspective;

    public Logs(int perspective){
        _gradientNorms = new ArrayList<>();
        _rewards = new ArrayList<>();
        _averageRewards = new ArrayList<>();
        _perspective = perspective;
    }

    public void logGradientNorm(Double norm){
        _gradientNorms.add(norm);
    }

    public void logReward(Double reward){
        _rewards.add(reward);
        if(_averageRewards.size() == 0){
            _averageRewards.add(reward);
        } else {
            double lastAverage = _averageRewards.getLast();
            double newAverage = (lastAverage * (_averageRewards.size()) + reward) / (_averageRewards.size() + 1);
            _averageRewards.add(newAverage);
        }
    }

    public String getDataString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Perspective: " + _perspective + "\n");
        sb.append("Gradient Norms: ");
        for(int i = 0; i < _gradientNorms.size(); i++){
            sb.append(_gradientNorms.get(i));
            if(i < _gradientNorms.size() - 1){
                sb.append(",");
            }
        }
        sb.append("\n");
        sb.append("Rewards: ");
        for(int i = 0; i < _rewards.size(); i++){
            sb.append(_rewards.get(i));
            if(i < _rewards.size() - 1){
                sb.append(",");
            }
        }
        sb.append("\n");
        sb.append("Average Rewards: ");
        for(int i = 0; i < _averageRewards.size(); i++){
            sb.append(_averageRewards.get(i));
            if(i < _averageRewards.size() - 1){
                sb.append(",");
            }
        }
        sb.append("\n");

        return sb.toString();
    }
}
