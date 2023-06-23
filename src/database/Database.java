package database;

import java.util.Map;
import service.FilePath;
import service.ReadCSVFile;

public class Database {

  public static Map<String, String> loginMap = ReadCSVFile.toMap(FilePath.FILE_CLIENTS);
  public static Map<String, String> countryMap = ReadCSVFile.toMap(FilePath.FILE_COUNTRIES);
}
