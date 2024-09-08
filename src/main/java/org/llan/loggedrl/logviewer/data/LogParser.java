package org.llan.loggedrl.logviewer.data;

    import java.io.BufferedReader;
    import java.io.File;
    import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogParser {

    private static final String _location = System.getProperty("user.dir") + "\\src\\main\\resources\\org\\llan\\loggedrl\\data\\";

    public static Log[] parseLogFile(String filePath) {
        File file = new File(_location + filePath);
        return parseLogFile(file);
    }

    public static Log[] parseLogFile(File file) {
        List<List<String>> logs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            List<String> currentLog = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if(line.startsWith("#")) {
                    logs.add(currentLog);
                    currentLog = new ArrayList<>();
                } else {
                    currentLog.add(line);
                }
            }
            logs.add(currentLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parse(logs);
    }

    private static Log[] parse(List<List<String>> logData){
        Log[] logs = new Log[logData.size()];
        for(int i = 0; i < logData.size(); i++){
            logs[i] = new Log();
            logs[i].parse(logData.get(i));
        }
        return logs;
    }
}
