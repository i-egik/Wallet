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
        showMenu();
    }

    public static String showMenu() {

        UserManager userManager = new StandardUserManager();
        UserManager.User user = null;
        String command = null;
        try (Scanner scanner = new Scanner(System.in)) {
            while (!"exit".equals(command)) {
                command = scanner.nextLine().trim();

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
                } else if ("-".equals(command) && user != null) { //прописать чтоб товарищ не снял больше шекелей чем у него в загашнике
                    String categoryName = scanner.nextLine().trim();
                    double spendMoney = scanner.nextDouble();
                    if (spendMoney < user.manager.getLimit(user.name, categoryName)) {
                        user.wallet.spendMoney(spendMoney, categoryName);
                    }
                } else if ("history+".equals(command) && user != null) {
                    user.wallet.getHistory(null, null, scanner.nextLine().trim(), Wallet.Operation.Add).forEach(history -> {
                        System.out.println("+ " + history.anyDate + " " + history.money);
                    });
                } else if ("history-".equals(command) && user != null) {
                    user.wallet.getHistory(null, null, scanner.nextLine().trim(), Wallet.Operation.Spend).forEach(history -> {
                        System.out.println("- " + history.anyDate + " " + history.money);
                    });
                } else if ("total+".equals(command) && user != null) {
                    double summAdd = 0;
                    for (Wallet.History history : user.wallet.getHistory(null, null, null, Wallet.Operation.Add)) {
                        summAdd += history.money;
                    }
                    System.out.println("total sum is " + summAdd);
                } else if ("total-".equals(command) && user != null) {
                    double summSpent = 0;
                    for (Wallet.History history : user.wallet.getHistory(null, null, null, Wallet.Operation.Spend)) {
                        summSpent += history.money;
                    }
                    System.out.println("total spent is " + summSpent);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
