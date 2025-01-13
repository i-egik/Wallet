package wallet;

import wallet.standart.StandardCategoryManager;
import wallet.standart.StandardUserManager;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        UserManager userManager = new StandardUserManager();
        userManager.registration("Pepper", "papperpassword");

        UserManager.User user = userManager.authentication("Pepper", "papperpassword");

        user.manager.addCategory("Pepper", "salary", .0);
        user.manager.delCategory("Pepper", "salary");
        // добавить / снять бабосики
        user.wallet.addMoney(5000, "salary");
        user.wallet.spendMoney(100, "beer");

        user.wallet.getBalance();

    }

    final String showMenu() {
        UserManager userManager = new StandardUserManager();
        UserManager.User user = null;
        String command = null;
        try (Scanner scanner = new Scanner(System.in)) {
            while (!"exit".equals(command)) {
                command = scanner.nextLine().trim();
                Double spendMoney = null;
                String categoryName = null;
                if ("login".equals(command)) {
                    System.out.println("username ");
                    String username = scanner.nextLine().trim();
                    System.out.println("password");
                    String password = scanner.nextLine().trim();
                    user = userManager.authentication(username, password);
                } else if ("registration".equals(command)) {
                    System.out.println("username ");
                    String username = scanner.nextLine().trim();
                    System.out.println("password");
                    String password = scanner.nextLine().trim();
                    userManager.registration(username, password);
                    user = userManager.authentication(username, password);
                } else if ("add".equals(command) && user != null) {
                    System.out.println("write name and limit");
                    user.manager.addCategory(user.name, scanner.nextLine().trim(), scanner.nextDouble());
                } else if ("del".equals(command) && user != null) {
                    System.out.println("delet category");
                    user.manager.delCategory(user.name, scanner.nextLine().trim());
                } else if ("+".equals(command) && user != null) {
                    user.wallet.addMoney(scanner.nextDouble(), scanner.nextLine().trim());
                } else if ("-".equals(command) && user != null && spendMoney < user.manager.getLimit(user.name, categoryName)) { //прописать чтоб товарищ не снял больше шекелей чем у него в загашнике
                    categoryName = scanner.nextLine().trim();
                    spendMoney = scanner.nextDouble();
                    user.manager.getLimit(user.name, categoryName);
                    user.wallet.spendMoney(scanner.nextDouble(), scanner.nextLine().trim());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}
