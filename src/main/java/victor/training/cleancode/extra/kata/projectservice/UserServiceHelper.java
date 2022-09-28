package victor.training.cleancode.extra.kata.projectservice;

public interface UserServiceHelper {
   void sendUserToServicesOnCreate(ProjectServiceDto projectServiceDTO, Project project, MessageAction messageAction, User user, ProjectUserDto projectUser, String name);
}
