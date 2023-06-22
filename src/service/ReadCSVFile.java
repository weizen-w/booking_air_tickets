package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadCSVFile {

  public static final String SEPARATOR = ";";

  public static Map<String, String> readLoginsClients() throws FileNotFoundException {
    if (!FilePath.FILE_CLIENTS.exists()) {
      throw new FileNotFoundException(
          "Файл не найден или указан не верный путь" + FilePath.FILE_CLIENTS);
    }
    Map<String, String> map = new HashMap<>();
    Scanner sc = new Scanner(new FileReader(FilePath.FILE_CLIENTS));
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      int indexSep = line.indexOf(SEPARATOR);
      String login = line.substring(0, indexSep);
      String password = line.substring(indexSep + 1); // не включая разделитель
      map.put(login, password);
    }
    sc.close();
    return map;
  }
}
