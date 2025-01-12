package wallet;

import java.util.List;

public interface UserManager {

    void registration(String name, String password);

    User authentication(String name, String password);

    final class User {
        public User(String name, Wallet wallet, CategoryManager manager) {
            this.name = name;
            this.wallet = wallet;
            this.manager = manager;
        }

        final String name;
        final Wallet wallet;
        final CategoryManager manager;
    }

}
