package wallet.standart;

import wallet.Wallet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StandardWallet implements Wallet {

    final List<History> history = new ArrayList<>();


    private double value;

    @Override
    public double getBalance() {
        return value;
    }

    @Override
    public List<History> getHistory(LocalDateTime fromDate, LocalDateTime toDate, String category, Operation operation) {
        return history;
    }

    @Override
    public void addMoney(double addMoney, String category) {
        value += addMoney;
        history.add(new History(LocalDateTime.now(), category, Operation.Add, addMoney));
    }

    @Override
    public void spendMoney(double spendMoney, String category) {
        value -= spendMoney;
        history.add(new History(LocalDateTime.now(), category, Operation.Spend, spendMoney));

    }
}
