package victor.training.cleancode.mass;

public class UserHolder {
  private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

  public static User getCurrentUser() {
    return currentUser.get();
  }

  public static void setCurrentUser(User user) {
    currentUser.set(user);
  }
}
