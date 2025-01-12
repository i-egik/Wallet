package wallet;

import wallet.standart.StandardUserManager;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Для того чтобы зарегестрироваться или войти в учётную запись, введите в консоли ВОЙТИ или СОЗДАТЬ");
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new StandardUserManager();
        userManager.registration(scanner.nextLine(), scanner.nextLine());


        UserManager.User user = userManager.authentication(scanner.nextLine(), scanner.nextLine());

        System.out.println("Для того чтобы создать новую категорию расходов, введите в консоли последовательно: ДОБАВИТЬ, НАЗВАНИЕ КАТЕГОРИИ(имя), ЛИМИТ БЮДЖЕТА(число)");
        user.manager.addCategory("Pepper", "salary", .0);
        user.manager.delCategory("Pepper", "salary");
        // добавить / снять бабосики
        user.wallet.addMoney(5000, "salary");
        user.wallet.spendMoney(100, "beer");

        user.wallet.getBalance();

    }
    final String showMenu(){
        System.out.println("Для того чтобы зарегестрироваться или войти в учётную запись, введите в консоли ВОЙТИ или СОЗДАТЬ");
        System.out.println("Для того чтобы создать новую категорию расходов, введите в консоли последовательно: ДОБАВИТЬ, НАЗВАНИЕ КАТЕГОРИИ(имя), ЛИМИТ БЮДЖЕТА(число)");
        System.out.println("Для того чтобы удалить категорию, введите в консоли последовательно: УДАЛИТЬ, НАЗВАНИЕ КАТЕГОРИИ(имя)");
        return null;
    }




}
