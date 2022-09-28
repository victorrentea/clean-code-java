package victor.training.cleancode.extra.kata.projectservice;

public class UserInAProject {
    private final ProjectUserDto projectUser;
    private final Project project;

    public UserInAProject(ProjectUserDto projectUser, Project project) {
        this.projectUser = projectUser;
        this.project = project;
    }

    public ProjectUserDto getProjectUser() {
        return projectUser;
    }

    public Project getProject() {
        return project;
    }
}
