package victor.training.cleancode.kata.projectservice;

import java.util.List;

public interface ProjectServicesRepo {
   List<ProjectServices> getProjectServicesByProjectId(Long id);
   ProjectServices findByServiceAndProject(Service service, Project project);
}
