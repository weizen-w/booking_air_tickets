package service;

import java.io.File;

public class FilePath {

  public static final String RES_FOLDER = "res/";
  public static final String CLIENTS_FOLDER = RES_FOLDER + "clients/";
  public static final File FILE_CLIENTS = new File(CLIENTS_FOLDER + "loginsClients.csv");
  public static final File FILE_RESERVATIONS = new File("/reservations.csv");
  public static final File PERSONAL_INFO = new File("/personalInfo.csv");
  public static final String LOCATION_FOLDER = RES_FOLDER + "location/";
  public static final File FILE_COUNTRIES = new File(LOCATION_FOLDER + "countries.csv");
  public static final File FILE_CITIES = new File("/cities.csv");
  public static final File FILE_AIRPORTS = new File("/airports.csv");
  public static final File FILE_FLIGHTS = new File("/flights.csv");
}
