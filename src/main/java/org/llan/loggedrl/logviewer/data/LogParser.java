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
        List<String> logLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(_location + filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.startsWith("#")) continue;
                logLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parse(logLines);
    }

    public static Log[] parseLogFile(File file) {
        List<String> logLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.startsWith("#")) continue;
                logLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parse(logLines);
    }

    private static Log[] parse(List<String> logLines){
        Log[] logs = new Log[logLines.size() / 4];
        for(int i = 0; i < logLines.size() / 4; i++){
            logs[i] = new Log();
            logs[i].parse(logLines.subList(4 * i, 4 * (i + 1)));
        }
        return logs;
    }
}
