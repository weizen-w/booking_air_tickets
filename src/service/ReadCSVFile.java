package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReadCSVFile {

  public static final String SEPARATOR = ";";

  public static Map<String, String> toMap(File file) {
    Scanner sc;
    try {
      sc = new Scanner(new FileReader(file));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    Map<String, String> map = new HashMap<>();
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      int indexSep = line.indexOf(SEPARATOR);
      String key = line.substring(0, indexSep);
      String value = line.substring(indexSep + 1); // не включая разделитель
      map.put(key, value);
    }
    sc.close();
    return map;
  }
}
