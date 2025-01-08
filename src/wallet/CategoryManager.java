package wallet;

import java.util.List;

public interface CategoryManager {
    List<Category> categories(String userName);
    void addCategory(String userName, String categoryName, double limit);
    void delCategory(String userName, String categoryName);

    final class Category{
        final String name;

        public Category(String name, double limit) {
            this.name = name;
            this.limit = limit;
        }

        final double limit;

    }

}
