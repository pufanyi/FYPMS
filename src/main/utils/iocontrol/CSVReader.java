package main.utils.iocontrol;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class for reading CSV files and returning their
 * contents as a list of rows, each represented as a list of
 * strings.
 */
public class CSVReader {

    /**
     * Reads a CSV file and returns its contents as a list of rows,
     * each represented as a list of strings.
     *
     * @param filePath  the path of the CSV file to be read
     * @param hasHeader a boolean indicating whether the CSV file has
     *                  a header row
     * @return a list of rows, each represented as a list of strings
     */
    public static List<List<String>> read(String filePath, boolean hasHeader) {
        List<List<String>> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            if (hasHeader) {
                // Skip the first line, assuming it's a header
                br.readLine();
            }
            while ((line = br.readLine()) != null) {
                List<String> row = new ArrayList<>();
                // Define a regex pattern to match the CSV values
                Pattern pattern = Pattern.compile("\"([^\"]*)\"|(?<=,|^)([^,]*)(?=,|$)");
                Matcher matcher = pattern.matcher(line);
                // Extract the CSV values from the line
                while (matcher.find()) {
                    String value = matcher.group().replace("\"", "");
                    // Ignore spaces at the beginning of the value
                    while (value.startsWith(" ")) {
                        value = value.substring(1);
                    }
                    // Ignore spaces at the end of the value
                    while (value.endsWith(" ")) {
                        value = value.substring(0, value.length() - 1);
                    }
                    row.add(value);
                }
                // Add the row to the list if it's not empty or null
                if (row.size() > 0 && !row.get(0).equals("")) {
                    list.add(row);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return list;
    }
}