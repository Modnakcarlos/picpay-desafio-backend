package picpaydesafiobackend.common.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String criptografaToBCrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verificaBCrypt(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
