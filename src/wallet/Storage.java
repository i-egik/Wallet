package wallet;

public interface Storage {
    void stockWallet();
    void stockUser();
    void stockCategory();

    //создается папка User c именем пользователя. в нём хранятся:
            //файл с инфой о пользователе (String userName, String password)
            //файл с инфой о кошельке (ссылки на методы addMoney, spendMoney и на историю операций)
            //файл с категориями (или папка с файлами категорий?)


}
