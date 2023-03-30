/**
 * This is a CSV reader class that reads data from a CSV file and returns it as a list of rows,
 * where each row is a list of strings representing the columns.
 */
package main.utils.iocontrol;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReader {

    /**
     * Reads the data from the specified CSV file and returns it as a list of rows.
     *
     * @param filePath The path of the CSV file to read.
     * @param hasHeader A flag indicating whether the CSV file has a header row.
     * @return A list of rows, where each row is a list of strings representing the columns.
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
                    row.add(value);
                }
                // Add the row to the list if it's not empty or null
                if (row.size() > 0 && !row.get(0).equals("")) {
                    list.add(row);
                }
            }
        } catch (IOException e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }

        return list;
    }
}
