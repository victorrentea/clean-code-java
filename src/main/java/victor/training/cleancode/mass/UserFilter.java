package victor.training.cleancode.mass;

import jakarta.servlet.*;

import java.io.IOException;

public class UserFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    User user = fromHeaders(request);
    UserHolder.setCurrentUser(user);
  }

  private User fromHeaders(ServletRequest request) {
    //pretend
    User user = new User();
    user.setUsername("jdoe");
    user.setFullName("Jane Doe");
    return user;
  }
}
