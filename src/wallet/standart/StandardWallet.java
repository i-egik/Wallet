package wallet.standart;

import wallet.CategoryManager;
import wallet.Wallet;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class StandardWallet implements Wallet {

    final String userName;

    final List<History> history = new ArrayList<>();


    private double value;

    public StandardWallet(String userName) {
        this.userName = userName;
        readWallet();
    }

    private void synchronize() {
        File file = new File(userName, "history.txt");
        if (file.exists()) {
            file.delete();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (History h : history) {
                writer.append(String.format("%d:%s:%s:%f", dateTime(h.anyDate), h.category, h.operation, h.money));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readWallet() {
        history.clear();
        value = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(userName, "history.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] split = line.split(":");
                LocalDateTime dt = dateLong(Long.parseLong(split[0].trim()));
                String categoryName = split[1].trim();
                Operation operation = Operation.valueOf(split[2].trim());
                double money = NumberFormat.getInstance().parse(split[3].trim()).doubleValue();
                if (operation == Operation.Add) {
                    value += money;
                } else {
                    value -= money;
                }

                history.add(new History(dt, categoryName, operation, money));
                NumberFormat.getInstance(Locale.ENGLISH).parse(split[3]);
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static long dateTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    private static LocalDateTime dateLong(long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }

    @Override
    public double getBalance() {
        return value;
    }

    @Override
    public List<History> getHistory(LocalDateTime fromDate, LocalDateTime toDate, String category, Operation operation) {
        Stream<History> stream = history.stream();
        if (category != null) {
            stream = stream.filter(history1 -> history1.category.equals(category));
        }
        if (operation != null) {
            stream = stream.filter(history1 -> history1.operation.equals(operation));
        }
        if (fromDate != null) {
            stream = stream.filter(history1 -> history1.anyDate.isAfter(fromDate));
        }
        return stream.toList();
    }

    @Override
    public void addMoney(double addMoney, String category) {
        value += addMoney;
        history.add(new History(LocalDateTime.now(), category, Operation.Add, addMoney));
        synchronize();
    }

    @Override
    public void spendMoney(double spendMoney, String category) {
        value -= spendMoney;
        history.add(new History(LocalDateTime.now(), category, Operation.Spend, spendMoney));
        synchronize();
    }
}
