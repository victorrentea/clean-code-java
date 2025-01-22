package victor.training.cleancode.java.kata.projectservice;

import java.util.Optional;

public interface UserRepo {
   Optional<User> findByUuid(String cuid);
}
