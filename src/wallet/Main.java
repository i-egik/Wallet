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
        chooseYourDastiny();
    }

    public static String chooseYourDastiny() {

        System.out.println("Добро пожаловать в вашу личную казну Милорд");
        System.out.println("Чтобы создать новое хранилище, введите в консоли registration");
        System.out.println("Чтобы войти в сокровищницу, введите в консоли login");
        System.out.println("Чтобы создать новый список поступлений или трат, введите в консоли add");
        System.out.println("Чтобы казначей выделил золото на что-то, впишите - и название статьи");
        System.out.println("Чтобы казначей внёс доходы, впишите + и название статьи");
        System.out.println("Чтобы закрыть более ненужную статью, впишите del и название статьи");
        System.out.println("Чтобы казначей отчитался Вам по Вашим расходам, впишите history- ");
        System.out.println("Чтобы казначей отчитался Вам по Вашим доходам, впишите history+ ");
        return " ";
    }

    public static String showMenu() {

        UserManager userManager = new StandardUserManager();
        UserManager.User user = null;
        String command = null;
        chooseYourDastiny();
        try (Scanner scanner = new Scanner

                (System.in)) {
            while (!"exit".equals(command)) {
                command = scanner.nextLine().trim();

                if ("login".equals(command)) {
                    System.out.println("Впишите своё имя Милорд ");
                    String username = scanner.nextLine().trim();
                    System.out.println("Впишите кодовое слово Милорд");
                    String password = scanner.nextLine().trim();
                    user = userManager.authentication(username, password);
                    chooseYourDastiny();
                } else if ("registration".equals(command)) {
                    System.out.println("Представьтесь Милорд ");
                    String username = scanner.nextLine().trim();
                    System.out.println("Впишите кодовое слово Милорд");
                    String password = scanner.nextLine().trim();
                    userManager.registration(username, password);
                    user = userManager.authentication(username, password);
                    chooseYourDastiny();
                } else if ("add".equals(command) && user != null) {
                    System.out.println("Впишите название категории и потом лимит по ней");
                    user.manager.addCategory(user.name, scanner.nextLine().trim(), scanner.nextDouble());
                    chooseYourDastiny();
                } else if ("del".equals(command) && user != null) {
                    System.out.println("Впишите название удаляемой категории");
                    user.manager.delCategory(user.name, scanner.nextLine().trim());
                    chooseYourDastiny();
                } else if ("+".equals(command) && user != null) {
                    System.out.println("Впишите сколько нужно доавить и в какое отделение положить Милорд");
                    user.wallet.addMoney(scanner.nextDouble(), scanner.nextLine().trim());
                    System.out.println("Золото доставлено в казну Милорд");
                } else if ("-".equals(command) && user != null) { //прописать чтоб товарищ не снял больше шекелей чем у него в загашнике
                    String categoryName = scanner.nextLine().trim();
                    double spendMoney = scanner.nextDouble();
                    if (spendMoney < user.manager.getLimit(user.name, categoryName)) {
                        user.wallet.spendMoney(spendMoney, categoryName);
                        System.out.println("Вино и женщины обходятся слишком дорого короне Милорд");
                    }
                    chooseYourDastiny();
                } else if ("history+".equals(command) && user != null) {
                    user.wallet.getHistory(null, null, scanner.nextLine().trim(), Wallet.Operation.Add).forEach(history -> {
                        System.out.println("+ " + history.anyDate + " " + history.money);
                    });
                    chooseYourDastiny();
                } else if ("history-".equals(command) && user != null) {
                    user.wallet.getHistory(null, null, scanner.nextLine().trim(), Wallet.Operation.Spend).forEach(history -> {
                        System.out.println("- " + history.anyDate + " " + history.money);
                    });
                    chooseYourDastiny();
                } else if ("total+".equals(command) && user != null) {
                    double summAdd = 0;
                    for (Wallet.History history : user.wallet.getHistory(null, null, null, Wallet.Operation.Add)) {
                        summAdd += history.money;
                    }
                    System.out.println("total sum is " + summAdd);
                    chooseYourDastiny();
                } else if ("total-".equals(command) && user != null) {
                    double summSpent = 0;
                    for (Wallet.History history : user.wallet.getHistory(null, null, null, Wallet.Operation.Spend)) {
                        summSpent += history.money;
                    }
                    System.out.println("total spent is " + summSpent);
                    chooseYourDastiny();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
