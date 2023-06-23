package aviation;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import location.City;
import menu.MenuMethods;
import service.FilePath;
import service.ReadCSVFile;

public class Airport {

  public final String code;
  public final City city;
  public final String airportFolder;

  public Map<String, String> flightMap;

  public Airport(String code, City city) {
    this.code = code;
    this.city = city;
    airportFolder = city.cityFolder + code + "/";
    File filePathToFlights = new File(airportFolder + FilePath.FILE_FLIGHTS);
    flightMap = ReadCSVFile.toMap(filePathToFlights);
  }

  public static Airport select(City city, Scanner sc) {
    String choice = MenuMethods.selectMenu(city.airportList, sc);
    return new Airport(city.airportList.get(choice), city);
  }
}
