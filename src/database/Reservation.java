package database;

public class Reservation {

  public static final String SEPARATOR = ";";

  private static int numberCounter = 1;
  public String number;
  public final String clientLogin;
  public int amountPassenger;
  public double priceTotal;

  public Reservation(String clientLogin, int amountPassenger) {
    this.clientLogin = clientLogin;
    this.amountPassenger = amountPassenger;
    number = clientLogin + numberCounter;
    ++numberCounter;
  }

  public Reservation(String number, String clientLogin, int amountPassenger, double priceTotal) {
    this.number = number;
    ++numberCounter;
    this.clientLogin = clientLogin;
    this.amountPassenger = amountPassenger;
    this.priceTotal = priceTotal;
  }

  @Override
  public String toString() {
    return "Reservation{" +
        "number='" + number + '\'' +
        ", clientLogin='" + clientLogin + '\'' +
        ", amountPassenger=" + amountPassenger +
        ", priceTotal=" + priceTotal +
        '}';
  }

  public String toWriteCSV() {
    return number + SEPARATOR + clientLogin + SEPARATOR + amountPassenger + SEPARATOR + priceTotal;
  }
}
