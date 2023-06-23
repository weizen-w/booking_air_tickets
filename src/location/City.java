package location;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import menu.MenuMethods;
import service.FilePath;
import service.ReadCSVFile;

public class City {

  public final String title;
  public final Country country;
  public final String cityFolder;

  public Map<String, String> airportList;

  public City(String title, Country country) {
    this.title = title;
    this.country = country;
    cityFolder = country.countryFolder + title + "/";
    File filePathToAirport = new File(cityFolder + FilePath.FILE_AIRPORTS);
    airportList = ReadCSVFile.toMap(filePathToAirport);
  }

  public static City select(Country country, Scanner sc) {
    String choice = MenuMethods.selectMenu(country.cityMap, sc);
    return new City(country.cityMap.get(choice), country);
  }
}
