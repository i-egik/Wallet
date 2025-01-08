package wallet.standart;

import wallet.CategoryManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StandartCaregoryManager implements CategoryManager {
    final List<Category> category = new ArrayList<>();


    private String categoryName;

    @Override
    public List<Category> categories(String userName) {
        File categories = new File("Categories");
        try {
            categories.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return (List<Category>) categories;
    }

    @Override
    public void addCategory(String userName, String categoryName, double limit) {


    }

    @Override
    public void delCategory(String userName, String categoryName) {

    }
}
