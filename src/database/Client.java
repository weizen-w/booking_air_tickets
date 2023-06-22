package database;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

  public static final String SEPARATOR = ";";
  public String login;
  public String password;
  public String firstName;
  public String lastName;
  public String passNumber;

  public SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  public Date birthdate;

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

  public String stringToWriteCSV() {
    return login + SEPARATOR + password;
  }
}
