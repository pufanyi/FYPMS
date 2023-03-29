package test.utils;

import main.utils.config.Location;
import main.utils.iocontrol.TSVReader;

import java.util.List;

public class TSVReaderTest {
    public static void main(String[] args) {
        List<List<String>> list = TSVReader.read(Location.LOCATION + "\\resources\\StudentList.csv", true);
        for (List<String> row : list) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
