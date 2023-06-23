package aviation;

import database.Reservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Flight {

  private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
  private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
  public final String number;
  public final String cityDeparture;
  public final String cityArrival;
  public Airplane airplane;
  public Date date = new Date();
  public Date timeDeparture;
  public Date timeArrival;
  public Date timeInFly;

  public List<Reservation> reservationList;

  public Flight(String number, String cityDeparture, String cityArrival, Airplane airplane,
      String timeDeparture, String timeArrival) throws ParseException {
    this.number = number;
    this.cityDeparture = cityDeparture;
    this.cityArrival = cityArrival;
    this.airplane = airplane;
    this.timeDeparture = timeFormat.parse(timeDeparture);
    this.timeArrival = timeFormat.parse(timeArrival);
    timeInFly = new Date(this.timeArrival.getTime() - this.timeDeparture.getTime());
  }

  @Override
  public String toString() {
    return "number='" + number + '\'' +
        ", cityDeparture='" + cityDeparture + '\'' +
        ", cityArrival='" + cityArrival + '\'' +
        ", airbus=" + airplane +
        ", timeDeparture=" + timeDeparture +
        ", timeArrival=" + timeArrival +
        '}';
  }
}
