package wallet;

import wallet.standart.StandardUserManager;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        UserManager userManager = new StandardUserManager();
        userManager.registration("Pepper", "papperpassword");
        UserManager.User user = userManager.authentication("Pepper", "papperpassword");
        user.manager.addCategory("Pepper", "salary", .0);
        user.wallet.addMoney(5000, "salary");
        user.wallet.getBalance();

    }
    final String showMenu(){
        System.out.println("Для того чтобы зарегестрироваться или войти в учётную запись, введите в консоли ВОЙТИ или СОЗДАТЬ");
        System.out.println("Для того чтобы создать новую категорию расходов, введите в консоли последовательно: ДОБАВИТЬ, НАЗВАНИЕ КАТЕГОРИИ(имя), ЛИМИТ БЮДЖЕТА(число)");
        System.out.println("Для того чтобы удалить категорию, введите в консоли последовательно: УДАЛИТЬ, НАЗВАНИЕ КАТЕГОРИИ(имя)");
        return null;
    }




}
