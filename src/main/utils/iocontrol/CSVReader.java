package main.utils.iocontrol;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReader {
    public static List<List<String>> read(String filePath, boolean hasHeader) {
        List<List<String>> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            if (hasHeader) {
                // Skip the first line
                br.readLine();
            }
            while ((line = br.readLine()) != null) {
                List<String> row = new ArrayList<>();
                Pattern pattern = Pattern.compile("\"([^\"]*)\"|(?<=,|^)([^,]*)(?=,|$)");
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String value = matcher.group().replace("\"", "");
                    row.add(value);
                }
                if (row.size() > 0 && !row.get(0).equals("")) {
                    list.add(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
