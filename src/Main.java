import java.util.Scanner;
import menu.HeadMenu;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (true) {
      HeadMenu.apply(sc);
    }
  }
}
