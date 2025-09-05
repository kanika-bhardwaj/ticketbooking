package ticket.booking.util;
import org.mindrot.jbcrypt.Bcrypt;
public class UserServiceUtil {
    public static String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword,BCrypt.gensalt());
    }
    public static boolean checkPassword(String plainPassword,String hashedPassword){
        return BCrypt.checkpw(plainPassword,hashedPassword);
    }
}