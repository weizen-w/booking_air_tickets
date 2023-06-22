package menu;

import java.util.Map;
import java.util.Scanner;

public class MenuMethods {

  private MenuMethods() {
  }

  public static void printMenu(Map<String, String> map) {
    for (Map.Entry<String, String> rowMenu : map.entrySet()) {
      System.out.println(rowMenu.getKey() + ". " + rowMenu.getValue());
    }
  }

  public static String selectMenu(Map<String, String> map, Scanner sc) {
    printMenu(map);
    System.out.print("Введите номер меню, для выбора: ");
    String choice = sc.nextLine();
    while (!map.containsKey(choice)) {
      System.err.println("ERROR: \"IllegalArgumentException (Не корректный ввод)\": " + choice);
      System.err.println("-------------------------------------------------------------------");
      System.err.print("Повторите ввод. Введите правильный номер меню: ");
      choice = sc.nextLine();
    }
    return choice;
  }
}
