package org.llan.loggedrl.logviewer.data;

    import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogParser {

    private static final String _location = System.getProperty("user.dir") + "\\src\\main\\resources\\org\\llan\\loggedrl\\data\\";

    public static List<String> parseLogFile(String filePath) {
        List<String> logLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(_location + filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                logLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logLines;
    }
}
