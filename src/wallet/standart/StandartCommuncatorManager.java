package wallet.standart;

import wallet.Communicator;

import java.util.Scanner;

public class StandartCommuncatorManager implements Communicator {
    @Override
    public String inputStream() {
        Scanner inputStream = new Scanner(System.in);
        return inputStream.nextLine();
    }

    @Override
    public String outputStream() {
        System.out.println(inputStream());
        return inputStream();
    }
}
