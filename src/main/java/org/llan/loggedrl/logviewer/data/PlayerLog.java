package org.llan.loggedrl.logviewer.data;

import java.util.ArrayList;
import java.util.List;

public class PlayerLog {
    private List<Double> _gradientNorms;
    private List<Double> _rewards;
    private List<Double> _averageRewards;

    private int _perspective;

    public void parse(List<String> logLines){
        _gradientNorms = new ArrayList<>();
        _rewards = new ArrayList<>();
        _averageRewards = new ArrayList<>();
        _perspective = Integer.parseInt(logLines.get(0).split(":")[1].trim());
        for(int i = 1; i < logLines.size(); i++){
            String[] data = logLines.get(i).split(":");
            String header = data[0].trim();
            String[] values = data[1].split(",");
            List toAdd;
            switch(header){
                case "Gradient Norms":
                    toAdd = _gradientNorms;
                    break;
                case "Rewards":
                    toAdd = _rewards;
                    break;
                case "Average Rewards":
                    toAdd = _averageRewards;
                    break;
                default:
                    toAdd = null;
            }
            if(toAdd != null){
                for(int j = 0; j < values.length; j++){
                    toAdd.add(Double.parseDouble(values[j]));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "PlayerLog{" +
                "_averageRewards=" + _averageRewards +
                ", _gradientNorms=" + _gradientNorms +
                ", _rewards=" + _rewards +
                ", _perspective=" + _perspective +
                '}';
    }
}
