package location;

import database.Database;
import java.io.File;
import java.util.Map;
import java.util.Scanner;
import menu.MenuMethods;
import service.FilePath;
import service.ReadCSVFile;

public class Country {

  public final String title;
  public final String countryFolder;

  public Map<String, String> cityMap;

  public Country(String title) {
    this.title = title;
    countryFolder = FilePath.LOCATION_FOLDER + title + "/";
    File filePathToCities = new File(countryFolder + FilePath.FILE_CITIES);
    cityMap = ReadCSVFile.toMap(filePathToCities);
  }

  public static Country select(Scanner sc) {
    String choice = MenuMethods.selectMenu(Database.countryMap, sc);
    return new Country(Database.countryMap.get(choice));
  }
}
