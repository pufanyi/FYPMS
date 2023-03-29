package main.utils.iocontrol;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TSVReader {
    public static List<List<String>> read(String filePath, boolean hasHeader) {
        List<List<String>> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            if (hasHeader) {
                br.readLine();
            }
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                if (values.length == 0) {
                    continue;
                }
                List<String> row = new ArrayList<>();
                for (String value : values) {
                    row.add(value.trim());
                }
                list.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
