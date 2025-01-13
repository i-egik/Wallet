package wallet.standart;

import wallet.CategoryManager;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class StandardCategoryManager implements CategoryManager {
    final Map<String, Category> categories = new HashMap<>();


    @Override
    public double getLimit(String userName, String categoryName) {
        if(categories.containsKey(categoryName)){
            return categories.get(categoryName).limit;
        }
        return 0;
    }

    @Override
    public List<Category> categories(String userName) {
        categories.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(userName, "categories.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] split = line.split(":");
                Category category = new Category(split[0].trim(), Double.parseDouble(split[1].trim()));
                categories.put(category.name, category);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>(categories.values());
    }

    @Override
    public void addCategory(String userName, String categoryName, double limit) {
        categories.put(categoryName, new Category(categoryName, limit));
        synchronize(userName);
    }


    @Override
    public void delCategory(String userName, String categoryName) {
        categories.remove(categoryName);
        synchronize(userName);
    }

    private void synchronize(String userName) {
        File file = new File(userName, "categories.txt");
        if (file.exists()) {
            file.delete();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Category category : categories.values()) {
                writer.append(String.format("%s:%f", category.name, category.limit));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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




