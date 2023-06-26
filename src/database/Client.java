package database;

import aviation.Airplane;
import aviation.Airport;
import aviation.Flight;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import location.City;
import location.Country;
import service.FilePath;
import service.ReadCSVFile;

public class Client {

  public static final String SEPARATOR = ";";
  public String login;
  public String password;
  public String firstName;
  public String lastName;
  public String passNumber;
  public SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  public Date birthdate;

  public Set<Flight> reservationSet = new HashSet<>();

  public Client(String login, String password) throws FileNotFoundException {
    this.login = login;
    this.password = password;
  }

  public Client(String login, String password, String firstName, String lastName, String passNumber,
      String birthdate) throws ParseException {
    this.login = login;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.passNumber = passNumber;
    this.birthdate = formatter.parse(birthdate);
  }

  public void readMyReservations() {
    String fileReservationOfClient = FilePath.CLIENTS_FOLDER + login + FilePath.FILE_RESERVATIONS;
    Scanner sc;
    try {
      sc = new Scanner(new FileReader(fileReservationOfClient));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      String[] lineArray = line.split(SEPARATOR);
      String number = lineArray[0];
      Airport airportDeparture = new Airport(lineArray[1],
          new City(lineArray[2], new Country(lineArray[3])));
      String airportArrival = lineArray[4];
      Airplane airplane = new Airplane(lineArray[5]);
      String timeDeparture = lineArray[6];
      String timeArrival = lineArray[7];
      Flight flight;
      try {
        flight = new Flight(number, airportDeparture, airportArrival, airplane, timeDeparture,
            timeArrival);
      } catch (ParseException e) {
        throw new RuntimeException(e);
      }
      reservationSet.add(flight);
    }
    sc.close();
  }

  public void showMyReservations() {
    for (Flight flight : reservationSet) {
      flight.reservationList = Flight.readReservationList(flight);
      flight.reservationList.stream()
          .filter(x -> x.clientLogin.equals(login))
          .forEach(System.out::println);
    }
  }

  public String stringToWriteCSV() {
    return login + SEPARATOR + password;
  }
}
