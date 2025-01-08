package wallet.standart;

import wallet.UserManager;

import java.io.*;

public class StandardUserManager implements UserManager {

    @Override
    public void registration(String name, String password) {
        File dir = new File(name);
        if (dir.exists()) {
            throw new RuntimeException("User already exist");
        }
        dir.mkdirs();
        try (FileOutputStream output = new FileOutputStream(new File(dir, ".password"))) {
            output.write(password.getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User authentication(String name, String password) {
        try (FileInputStream intput = new FileInputStream(new File(name, ".password"))) {
            byte[] bytes = intput.readAllBytes();
            if (!password.equals(new String(bytes))) {
                throw new RuntimeException("bad password");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new User(name, null, null);
    }
}
