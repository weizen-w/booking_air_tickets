package service;

import java.io.File;

public class FilePath {

  public static final String RES_FOLDER = "res";
  public static final String CLIENTS_FOLDER = RES_FOLDER + "/clients";
  public static final File FILE_CLIENTS = new File(CLIENTS_FOLDER + "/loginsClients.csv");
  public static final File PERSONAL_INFO = new File("/personalInfo.csv");
}
