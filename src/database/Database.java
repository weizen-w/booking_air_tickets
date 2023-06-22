package database;

import java.io.IOException;
import java.util.Map;
import service.ReadCSVFile;

public class Database {

  public static Map<String, String> loginMap;

  static {
    try {
      loginMap = ReadCSVFile.readLoginsClients();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
