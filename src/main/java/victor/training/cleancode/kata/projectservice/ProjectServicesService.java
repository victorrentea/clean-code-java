package victor.training.cleancode.kata.projectservice;

import java.util.List;
import java.util.Optional;

public interface ProjectServicesService {
   List<ProjectServices> getProjectServicesByProjectId(Long id);
   Optional<ProjectServices> findByServiceAndProject(Service service, Project project);
}
