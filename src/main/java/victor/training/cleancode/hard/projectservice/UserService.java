package victor.training.cleancode.hard.projectservice;

import java.net.CookieHandler;
import java.util.Optional;

public interface UserService {
   Optional<User> findByCuid(String cuid);
}
