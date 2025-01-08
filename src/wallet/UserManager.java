package wallet;

import java.util.List;

public interface UserManager {

    void registration(String name, String password);

    User authentication(String name, String password);

    final class User {
        public User(String name, Wallet wallet, List<CategoryManager.Category> categorys) {
            this.name = name;
            this.wallet = wallet;
            this.categorys = categorys;
        }

        final String name;
        final Wallet wallet;
        final List<CategoryManager.Category> categorys;
    }

}
