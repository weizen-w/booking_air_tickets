package menu;

import aviation.Airport;
import database.Client;
import database.Database;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import location.City;
import location.Country;

public class ClientMenu {

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
  public static final String SEARCH_BOOKING = "1";
  public static final String MY_BOOKINGS = "2";
  public static final String LOGOUT = "0";
  public static final Map<String, String> description = new LinkedHashMap<>();

  static {
    description.put(SEARCH_BOOKING, "Поиск и бронирование билетов");
    description.put(MY_BOOKINGS, "Мои бронирования");
    description.put(LOGOUT, "Выйти из учетной записи");
  }

  public static final Map<String, Runnable> actions = new LinkedHashMap<>();

  static {
    Client client = checkLogin();
    actions.put(SEARCH_BOOKING, () -> search(client));
    actions.put(LOGOUT, System.out::close);
  }

  private ClientMenu() {
  }

  public static Client checkLogin() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Введите логин: ");
    String login = sc.nextLine();
    while (!Database.loginMap.containsKey(login)) {
      System.err.println("ERROR: Такого логина не существует: " + login);
      System.err.println("-------------------------------------------------------------");
      System.err.print("Повторите ввод. Введите логин: ");
      login = sc.nextLine();
    }
    System.out.print("Введите пароль: ");
    String password = sc.nextLine();
    int attempts = 3;
    while (!Database.loginMap.get(login).equals(password)) {
      --attempts;
      if (attempts == 0) {
        System.err.println("                      !!!! FAIL !!!!!");
        System.err.println("Введенные данные не совпадают. Пожалуйста, повторите ввод позже.\n");
        return null;
      }
      System.err.println("ERROR: Не верный пароль.");
      System.err.println("-------------------------------------------------------------");
      System.err.print("Повторите ввод (осталось попыток: " + attempts + "). Введите пароль: ");
      password = sc.nextLine();
    }
    System.out.println("СТАТУС: УСПЕШНО. Добро пожаловать '" + login + "' !\n");
    try {
      return new Client(login, password);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private static void search(Client client) {
    Scanner sc = new Scanner(System.in);
    System.out.println("+++ +++ Поиск и бронирование +++ +++");
    Country countryDeparture = Country.select(sc);
    City cityDeparture = City.select(countryDeparture, sc);
    Airport airportDeparture = Airport.select(cityDeparture, sc);
    Country countryArrival = Country.select(sc);
    City cityArrival = City.select(countryArrival, sc);
    Airport airportArrival = Airport.select(cityArrival, sc);
//    System.out.print("Введите дату в формате (dd.MM.yyyy): ");
//    Date date;
//    try {
//      date = dateFormat.parse(sc.nextLine());
//    } catch (ParseException e) {
//      throw new RuntimeException();
//    }
    airportDeparture.flightMap.entrySet().stream()
        .filter(x -> x.getValue().contains(airportArrival.code))
        .forEach(System.out::println);
//    System.out.print("Для бронирования введите номер рейса: ");
//    String num = sc.nextLine();
//    while (!airportDeparture.airportList.containsKey(num)) {
//      System.out.print("Вы ввели не существующий номер рейса, пожалуйста повторите ввод: ");
//      num = sc.nextLine();
//    }

  }

  public static void apply(Scanner sc) {
    String command = MenuMethods.selectMenu(description, sc);
    if (!actions.containsKey(command)) {
      throw new IllegalArgumentException("Некорректная команда: " + command);
    }
    Runnable action = actions.get(command);
    action.run();
  }
}
