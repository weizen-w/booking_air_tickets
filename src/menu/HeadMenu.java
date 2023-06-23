package menu;

import database.Database;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import service.FilePath;
import service.WriteCSVFile;

public class HeadMenu {

  public static final String REGISTRATION = "1";
  public static final String LOGIN_CLIENT = "2";
  public static final String LOGIN_ADMIN = "3";
  public static final String EXIT = "0";

  public static final Map<String, String> description = new LinkedHashMap<>();

  static {
    description.put(REGISTRATION, "Регистрация (новый пользователь)");
    description.put(LOGIN_CLIENT, "Авторизация (клиент)");
    description.put(LOGIN_ADMIN, "Авторизация (админ)");
    description.put(EXIT, "Выход");
  }

  public static final Map<String, Runnable> actions = new LinkedHashMap<>();

  static {
    actions.put(REGISTRATION, menu.HeadMenu::registration);
    actions.put(LOGIN_CLIENT, () -> {
      while (true) {
        ClientMenu.apply(new Scanner(System.in));
      }
    });
    actions.put(EXIT, () -> System.exit(0));
  }

  private HeadMenu() {
  }

  private static void registration() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Здравствуйте, пожалуйста для регистрации\nпридумайте Ваш логин: ");
    String login = sc.nextLine();
    while (Database.loginMap.containsKey(login)) {
      System.out.print("Извините, такой логин уже существует. Придумайте другой: ");
      login = sc.nextLine();
    }
    System.out.println("Логин успешно сохранен - |" + login + "|");
    System.out.print("Придумайте Ваш пароль: ");
    String password = sc.nextLine();
    System.out.print("Пожалуйста повторите ввод пароля, для подтверждения: ");
    String passwordControl = sc.nextLine();
    while (!password.equals(passwordControl)) {
      System.out.print("Пароли не совпали. Введите новый пароль: ");
      password = sc.nextLine();
      System.out.print("Пожалуйста повторите ввод пароля, для подтверждения: ");
      passwordControl = sc.nextLine();
    }
    System.out.println("Пароль успешно сохранен.");
    System.out.print("Введите Ваше имя: ");
    String firstName = sc.nextLine();
    while (!firstName.matches("[a-zA-Z а-яёА-ЯЁ]*")) {
      System.out.println("Имя не может содержать спец.символы или цифры!!!");
      System.out.println("Введите Ваше настоящее имя (как в паспорте)");
      firstName = sc.nextLine();
    }
    System.out.print("Введите Вашу фамилию: ");
    String secondName = sc.nextLine();
    while (!secondName.matches("[a-zA-Z а-яёА-ЯЁ]*")) {
      System.out.println("Фамилия не может содержать спец.символы или цифры!!!");
      System.out.println("Введите Вашу настоящую фамилию (как в паспорте)");
      secondName = sc.nextLine();
    }
    Database.loginMap.put(login, password);
    List<String> list = new ArrayList<>();
    list.add(firstName);
    list.add(secondName);
    File folderClient = new File(FilePath.CLIENTS_FOLDER + login);
    if (!folderClient.mkdir()) {
      System.out.println("Не удалось создать папку с именем '" + login + "'");
    }
    File file = new File(folderClient.getPath() + FilePath.PERSONAL_INFO);
    try {
      WriteCSVFile.writePersonalInfoClient(file, list);
      WriteCSVFile.writeLoginToClients();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Регистрация прошла успешно.");
    System.out.println("Благодарим Вас " + firstName + " за выбор нашего сервиса!!!");
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
