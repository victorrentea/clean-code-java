package victor.training.cleancode.java.kata.projectservice;

import java.util.List;

public interface ProjectServicesRepo {
   List<ProjectServices> findByProjectId(Long id);
   ProjectServices findByServiceAndProject(Service service, Project project);
}
