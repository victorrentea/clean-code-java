package victor.training.cleancode.kata.projectservice;

import java.util.Optional;

public interface UserRepo {
   Optional<User> findByUuid(String cuid);
}
