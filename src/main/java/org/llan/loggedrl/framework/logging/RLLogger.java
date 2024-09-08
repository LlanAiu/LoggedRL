package org.llan.loggedrl.framework.logging;

import java.io.*;

public class RLLogger {
    private final String _location = System.getProperty("user.dir") + "\\data\\";
    private String _filename = "log.txt";

    private Logs[] _logs;

    private static RLLogger _instance;

    public static RLLogger getInstance(){
        if(_instance == null)
            _instance = new RLLogger();
        return _instance;
    }

    public void setFilename(String filename){
        this._filename = filename;
    }

    public void setSize(int logs){
        _logs = new Logs[logs];
        for(int i = 0; i < logs; i++){
            _logs[i] = new Logs(i);
        }
    }

    private RLLogger(){
        _logs = new Logs[1];
        _logs[0] = new Logs(0);
    }

    public void recordDouble(int perspective, String key, double value){
        _logs[perspective].log(key, value);
    }

    public void logGradient(int perspective, double norm){
        _logs[perspective].logGradientNorm(norm);
    }

    public void averageGradient(int perspective){
        _logs[perspective].averageGradient();
    }

    public void logReward(int perspective, double reward){
        _logs[perspective].logReward(reward);
    }

    public String getDataString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < _logs.length; i++){
            sb.append(_logs[i].getDataString());
            if(i != _logs.length - 1)
                sb.append("# \n");
        }
        return sb.toString();
    }

    public void writeLog(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(_location + _filename))) {
            writer.write(getDataString());
            System.out.println("Log written to " + _location + _filename);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to write log to " + _location + _filename);
        }
    }
}
