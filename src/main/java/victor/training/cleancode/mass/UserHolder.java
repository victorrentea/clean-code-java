package victor.training.cleancode.mass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserHolder {
  private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

  public static User getCurrentUser() {
    return currentUser.get();
  }

  public static void setCurrentUser(User user) {
    currentUser.set(user);
  }

  public static User getCurrentUserOr(User user) {
    if (currentUser.get() != null) {
      return currentUser.get();
    }
    log.warn("We should have had here a user on the thread.", new RuntimeException());
//    TODO log a JFREvent with 0.01% perf impact
    return user;
  }
}
