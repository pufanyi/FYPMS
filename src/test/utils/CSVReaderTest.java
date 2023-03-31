package test.utils;

import main.utils.config.Location;
import main.utils.iocontrol.CSVReader;

import java.util.List;

/**
 * This class tests the {@link CSVReader} class.
 */
public class CSVReaderTest {
    /**
     * This method tests the {@link CSVReader} class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<List<String>> list = CSVReader.read(Location.LOCATION + "/resources/FacultyList.csv", true);
        for (List<String> row : list) {
            System.out.println("Row size: " + row.size());
            for (String value : row) {
                System.out.print(value + "\t | \t");
            }
            System.out.println();
        }
    }
}
