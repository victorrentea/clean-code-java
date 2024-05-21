package victor.training.cleancode.kata.projectservice;

public interface UserServiceHelper {
   void sendUserToServicesOnCreate(ProjectServicesDTO projectServicesDTO, Project project, MessageAction messageAction, User user, ProjectUserDTO projectUser, String name);
}
