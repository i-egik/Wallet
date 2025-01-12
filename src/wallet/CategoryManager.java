package wallet;

import java.io.FileNotFoundException;
import java.util.List;

public interface CategoryManager {
    List<Category> categories(String userName);
    void addCategory(String userName, String categoryName, double limit) throws FileNotFoundException;
    void delCategory(String userName, String categoryName);

    final class Category{
        public final String name;

        public Category(String name, double limit) {
            this.name = name;
            this.limit = limit;
        }

        public final double limit;

    }

}
