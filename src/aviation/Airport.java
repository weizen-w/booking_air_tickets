package aviation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import location.City;
import menu.MenuMethods;
import service.FilePath;

public class Airport {

  public static final String SEPARATOR = ";";

  public final String code;
  public City city;
  public String airportFolder;

  public Map<String, Flight> flightMap;

  public Airport(String code, City city) {
    this.code = code;
    this.city = city;
    airportFolder = city.cityFolder + code + "/";
  }

  public Airport(String code) {
    this.code = code;
  }

  public static Map<String, Flight> readFlightMap(Airport airportDeparture) {
    Scanner sc;
    try {
      sc = new Scanner(new FileReader(airportDeparture.airportFolder + FilePath.FILE_FLIGHTS));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    Map<String, Flight> map = new LinkedHashMap<>();
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      String[] arrayLine = line.split(SEPARATOR);
      String number = arrayLine[0];
      Airport airportDep = new Airport(arrayLine[1]);
      Airport airportArr = new Airport(arrayLine[2]);
      Airplane airplane = new Airplane(arrayLine[3]);
      String timeDeparture = arrayLine[4];
      String timeArrival = arrayLine[5];
      Flight flight;
      try {
        flight = new Flight(number, airportDep, airportArr, airplane, timeDeparture, timeArrival);
      } catch (ParseException e) {
        throw new RuntimeException(e);
      }
      map.put(number, flight);
    }
    sc.close();
    return map;
  }

  public static Airport select(City city, Scanner sc) {
    String choice = MenuMethods.selectMenu(city.airportList, sc);
    return new Airport(city.airportList.get(choice), city);
  }

  @Override
  public String toString() {
    return "Airport{" +
        "code='" + code + '\'' +
        ", city=" + city +
        '}';
  }
}
