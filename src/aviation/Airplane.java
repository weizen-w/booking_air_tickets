package aviation;


public class Airplane {

  public final String number;
  public final int seats = 220;
  public int seatFree = 220;
  public double price = 100.00;
  public boolean full = false;
  public boolean inFly = false;

  public Airplane(String number) {
    this.number = number;
  }

  @Override
  public String toString() {
    return "number= " + number;
  }
}
