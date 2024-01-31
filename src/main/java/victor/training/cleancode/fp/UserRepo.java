package victor.training.cleancode.fp;

import victor.training.cleancode.kata.projectservice.User;

import java.util.List;

public interface UserRepo {
  List<User> findAll();
}
