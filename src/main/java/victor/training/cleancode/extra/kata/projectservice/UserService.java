package victor.training.cleancode.extra.kata.projectservice;

import java.util.Optional;

public interface UserService {
   Optional<User> findByUuid(String cuid);
}
