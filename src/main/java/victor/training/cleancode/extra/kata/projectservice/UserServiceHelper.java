package victor.training.cleancode.extra.kata.projectservice;

public interface UserServiceHelper {
   void sendUserToServicesOnCreate(ProjectServicesDTO projectServicesDTO, Project project, MessageAction messageAction, User user, ProjectUserDTO projectUser, String name);
}
