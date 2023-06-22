package service;

import database.Client;
import database.Database;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class WriteCSVFile {

  public static void writeLoginToClients() throws IOException {
    if (Database.loginMap.isEmpty()) {
      throw new RuntimeException("Отсутствуют данные для записи");
    }
    Writer writer = new FileWriter(FilePath.FILE_CLIENTS);
    for (String keyLogin : Database.loginMap.keySet()) {
      Client client = new Client(keyLogin, Database.loginMap.get(keyLogin));
      writer.write(client.stringToWriteCSV() + "\n");
    }
    writer.close();
  }

  public static void writePersonalInfoClient(File file, List<String> list) throws IOException {
    Writer writer = new FileWriter(file);
    for (String str : list) {
      writer.write(str + "\n");
    }
    writer.close();
  }
}
