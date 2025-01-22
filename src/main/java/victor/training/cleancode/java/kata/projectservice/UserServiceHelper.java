package victor.training.cleancode.java.kata.projectservice;

public interface UserServiceHelper {
   void sendUserToServicesOnCreate(ProjectServicesDto projectServicesDTO, Project project, MessageAction messageAction, User user, UserDto projectUser, String name);
}
