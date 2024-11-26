package victor.training.cleancode.kata.projectservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExerciseTest {
  @Mock
  private ProjectServicesService projectServicesService;
  @Mock
  private UserService userService;
  @Mock
  private UserServiceHelper userServiceHelper;
  @Mock
  private ServiceService serviceService;

  @InjectMocks
  private Exercise exercise;

  @Test
  void sendsMessageOnCreateForAdminWithSubscribedService() {
    ProjectUserDTO projectUser = new ProjectUserDTO();
    projectUser.setRole(ProjectUserRoleType.ADMIN);
    Project project = new Project();
    Service service = new Service().setName("service");
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServices.Status.CREATED);
    projectServices.setService(service);
    User user = new User();

    when(projectServicesService.getProjectServicesByProjectId(any())).thenReturn(List.of(projectServices));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    verifyNoMoreInteractions(userServiceHelper);
  }

  @Test
  void sendsMessageOnCreateForAdmin() {
    ProjectUserDTO projectUser = new ProjectUserDTO();
    projectUser.setRole(ProjectUserRoleType.ADMIN);
    Project project = new Project();
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServices.Status.SUBSCRIBED);
    User user = new User();

    when(projectServicesService.getProjectServicesByProjectId(any())).thenReturn(List.of(projectServices));
    when(userService.findByUuid(any())).thenReturn(Optional.of(user));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    verify(userServiceHelper, times(1)).sendUserToServicesOnCreate(any(), eq(project), eq(MessageAction.CREATE), eq(user), eq(projectUser), eq("ADMIN"));
  }


  @Test
  void sendsMessageOnCreateForNonAdmin() {
    ProjectUserDTO projectUser = new ProjectUserDTO();
    projectUser.setRole(ProjectUserRoleType.VIEW);
    projectUser.setServices(List.of("service"));
    Project project = new Project();
    Service service = new Service().setName("service");
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServices.Status.SUBSCRIBED);
    User user = new User();

    when(serviceService.findAll()).thenReturn(Collections.singletonList(service));
    when(projectServicesService.findByServiceAndProject(any(), any())).thenReturn(projectServices);
    when(userService.findByUuid(any())).thenReturn(Optional.of(user));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    verify(userServiceHelper, times(1)).sendUserToServicesOnCreate(any(), eq(project), eq(MessageAction.CREATE), eq(user), eq(projectUser), eq("VIEW"));
  }

  @Test
  void doesNotSendMessageOnCreateForNonAdminWhenNoMatchingService() {
    ProjectUserDTO projectUser = new ProjectUserDTO();
    projectUser.setRole(ProjectUserRoleType.VIEW);
    projectUser.setServices(List.of("nonexistentService"));
    Project project = new Project();
    Service service = new Service().setName("service");
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServices.Status.SUBSCRIBED);
    User user = new User();

    when(serviceService.findAll()).thenReturn(Collections.singletonList(service));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    verifyNoInteractions(userServiceHelper);
  }

  @Test
  void sendsMessageOnCreateForContributorWithSubscribedService() {
    ProjectUserDTO projectUser = new ProjectUserDTO();
    projectUser.setRole(ProjectUserRoleType.CONTRIBUTOR);
    projectUser.setServices(List.of("service"));
    Project project = new Project();
    Service service = new Service().setName("service");
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServices.Status.SUBSCRIBED);
    projectServices.setService(service);
    User user = new User();

    when(serviceService.findAll()).thenReturn(Collections.singletonList(service));
    when(projectServicesService.findByServiceAndProject(any(), any())).thenReturn(projectServices);
    when(userService.findByUuid(any())).thenReturn(Optional.of(user));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    verify(userServiceHelper, times(1)).sendUserToServicesOnCreate(any(), eq(project), eq(MessageAction.CREATE), eq(user), eq(projectUser), eq("CONTRIBUTOR"));
  }

  @Test
  void setsServiceOnProjectServicesDTOForAdminWithSubscribedService() {
    ProjectUserDTO projectUser = new ProjectUserDTO();
    projectUser.setRole(ProjectUserRoleType.ADMIN);
    Project project = new Project();
    Service service = new Service().setName("service");
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServices.Status.SUBSCRIBED);
    projectServices.setService(service);
    User user = new User();

    when(projectServicesService.getProjectServicesByProjectId(any())).thenReturn(List.of(projectServices));
    when(userService.findByUuid(any())).thenReturn(Optional.of(user));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    ArgumentCaptor<ProjectServicesDTO> captor = ArgumentCaptor.forClass(ProjectServicesDTO.class);
    verify(userServiceHelper, times(1)).sendUserToServicesOnCreate(captor.capture(), eq(project), eq(MessageAction.CREATE), eq(user), eq(projectUser), eq("ADMIN"));
    assertEquals(service, captor.getValue().getService());
  }
}