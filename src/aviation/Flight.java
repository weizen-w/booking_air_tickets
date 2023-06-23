package aviation;

import database.Reservation;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Flight {

  public static final String SEPARATOR = ";";

  private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
  private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
  public final String number;
  public final Airport airportDeparture;
  public final Airport airportArrival;
  public Airplane airplane;
  public Date date = new Date();
  public Date timeDeparture;
  public Date timeArrival;
  public Date timeInFly;

  public List<Reservation> reservationList;
  public String filePathToReservations;

  public Flight(String number, Airport airportDeparture, Airport airportArrival, Airplane airplane,
      String timeDeparture, String timeArrival) throws ParseException {
    this.number = number;
    this.airportDeparture = airportDeparture;
    this.airportArrival = airportArrival;
    this.airplane = airplane;
    this.timeDeparture = timeFormat.parse(timeDeparture);
    this.timeArrival = timeFormat.parse(timeArrival);
    timeInFly = new Date(this.timeArrival.getTime() - this.timeDeparture.getTime());
    filePathToReservations = airportDeparture.airportFolder + "/" + number + ".csv";
  }

  public static List<Reservation> readReservationList(Flight flight) {
    Scanner sc;
    try {
      sc = new Scanner(new FileReader(flight.filePathToReservations));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    List<Reservation> list = new ArrayList<>();
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      String[] arrayLine = line.split(SEPARATOR);
      String number = arrayLine[0];
      String clientLogin = arrayLine[1];
      int amountPassenger = Integer.parseInt(arrayLine[2]);
      double priceTotal = Double.parseDouble(arrayLine[3]);
      list.add(new Reservation(number, clientLogin, amountPassenger, priceTotal));
      flight.airplane.seatFree -= amountPassenger;
    }
    sc.close();
    return list;
  }

  @Override
  public String toString() {
    return "number='" + number + '\'' +
        ", cityDeparture='" + airportDeparture + '\'' +
        ", cityArrival='" + airportArrival + '\'' +
        ", airplane=" + airplane +
        ", timeDeparture=" + timeDeparture +
        ", timeArrival=" + timeArrival +
        '}';
  }
}
