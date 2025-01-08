package wallet;

import java.time.LocalDateTime;
import java.util.List;

public interface Wallet {

    double getBalance();

    List<History> getHistory(LocalDateTime fromDate, LocalDateTime toDate, String category, Operation operation);

    void addMoney(double addMoney, String category);

    void spendMoney(double spendMoney, String category);


    enum Operation {
        Add, Spend
    }

    final class History {
        final LocalDateTime anyDate;
        final String category;
        final Operation operation;
        final double money;

        public History(LocalDateTime anyDate, String category, Operation operation, double money) {
            this.anyDate = anyDate;
            this.category = category;
            this.operation = operation;
            this.money = money;
        }
    }
}
