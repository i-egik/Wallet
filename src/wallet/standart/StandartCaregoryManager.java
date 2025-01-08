package wallet.standart;

import wallet.CategoryManager;

import java.io.*;
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
        File categoryDir = new File(categoryName);
        if (categoryDir.exists()) {
            throw new RuntimeException("Alrady exist");
        }
        try {
            categoryDir.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileOutputStream addCategory = new FileOutputStream(new File(categoryName))) {
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void delCategory(String userName, String categoryName) {
        File file = new File(categoryName);
        if (file.exists()) {
            file.delete();
        } throw new RuntimeException("Category is not available");



        }
    }










       /* File delCategory = new File("Categories");
        File afterDelCategory = new File("Categories");
        try {
            File inputFile = new File("Categories");
            File tempFile = new File("Categories");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String lineToRemove = "строка, которую нужно удалить";
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                // Если текущая строка не совпадает со строкой, которую нужно удалить, записываем её во временный файл
                if (!currentLine.equals(lineToRemove)) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
            writer.close();
            reader.close();
            inputFile.delete();  // Удаляем исходный файл
            tempFile.renameTo(inputFile);  // Переименовываем временный файл в исходное имя файла


        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Ошибка при работе с файлом.");
            ex.printStackTrace();
        }*/




