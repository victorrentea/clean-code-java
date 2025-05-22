package victor.training.cleancode.kata.projectservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExerciseTest {
  @Mock
  private ProjectServicesRepo projectServicesRepo;
  @Mock
  private UserRepo userRepo;
  @Mock
  private UserServiceHelper userServiceHelper;
  @Mock
  private ServiceRepo serviceRepo;

  @InjectMocks
  private Exercise exercise;

  @Test
  void sendsMessageOnCreateForAdminWithSubscribedService() {
    UserDto projectUser = new UserDto();
    projectUser.setRole(UserRole.ADMIN);
    Project project = new Project();
    Service service = new Service().setName("service");
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServices.Status.CREATED);
    projectServices.setService(service);
    User user = new User();

    when(projectServicesRepo.findByProjectId(any())).thenReturn(List.of(projectServices));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    verifyNoMoreInteractions(userServiceHelper);
  }

  @Test
  void sendsMessageOnCreateForAdmin() {
    UserDto projectUser = new UserDto();
    projectUser.setRole(UserRole.ADMIN);
    Project project = new Project();
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServices.Status.SUBSCRIBED);
    User user = new User();

    when(projectServicesRepo.findByProjectId(any())).thenReturn(List.of(projectServices));
    when(userRepo.findByUuid(any())).thenReturn(Optional.of(user));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    verify(userServiceHelper, times(1)).sendUserToServicesOnCreate(any(), eq(project), eq(MessageAction.CREATE), eq(user), eq(projectUser), eq("ADMIN"));
  }


  @Test
  void sendsMessageOnCreateForNonAdmin() {
    UserDto projectUser = new UserDto();
    projectUser.setRole(UserRole.VIEW);
    projectUser.setServices(List.of("service"));
    Project project = new Project();
    Service service = new Service().setName("service");
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServices.Status.SUBSCRIBED);
    User user = new User();

    when(serviceRepo.findAll()).thenReturn(Collections.singletonList(service));
    when(projectServicesRepo.findByServiceAndProject(any(), any())).thenReturn(projectServices);
    when(userRepo.findByUuid(any())).thenReturn(Optional.of(user));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    verify(userServiceHelper, times(1)).sendUserToServicesOnCreate(any(), eq(project), eq(MessageAction.CREATE), eq(user), eq(projectUser), eq("VIEW"));
  }

  @Test
  void doesNotSendMessageOnCreateForNonAdminWhenNoMatchingService() {
    UserDto projectUser = new UserDto();
    projectUser.setRole(UserRole.VIEW);
    projectUser.setServices(List.of("nonexistentService"));
    Project project = new Project();
    Service service = new Service().setName("service");
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServices.Status.SUBSCRIBED);
    User user = new User();

    when(serviceRepo.findAll()).thenReturn(Collections.singletonList(service));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    verifyNoInteractions(userServiceHelper);
  }

  @Test
  void sendsMessageOnCreateForContributorWithSubscribedService() {
    UserDto projectUser = new UserDto();
    projectUser.setRole(UserRole.CONTRIBUTOR);
    projectUser.setServices(List.of("service"));
    Project project = new Project();
    Service service = new Service().setName("service");
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServices.Status.SUBSCRIBED);
    projectServices.setService(service);
    User user = new User();

    when(serviceRepo.findAll()).thenReturn(Collections.singletonList(service));
    when(projectServicesRepo.findByServiceAndProject(any(), any())).thenReturn(projectServices);
    when(userRepo.findByUuid(any())).thenReturn(Optional.of(user));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    verify(userServiceHelper, times(1)).sendUserToServicesOnCreate(any(), eq(project), eq(MessageAction.CREATE), eq(user), eq(projectUser), eq("CONTRIBUTOR"));
  }

  @Test
  void setsServiceOnProjectServicesDTOForAdminWithSubscribedService() {
    UserDto projectUser = new UserDto();
    projectUser.setRole(UserRole.ADMIN);
    Project project = new Project();
    Service service = new Service().setName("service");
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServices.Status.SUBSCRIBED);
    projectServices.setService(service);
    User user = new User();

    when(projectServicesRepo.findByProjectId(any())).thenReturn(List.of(projectServices));
    when(userRepo.findByUuid(any())).thenReturn(Optional.of(user));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    ArgumentCaptor<ProjectServicesDto> captor = ArgumentCaptor.forClass(ProjectServicesDto.class);
    verify(userServiceHelper, times(1)).sendUserToServicesOnCreate(captor.capture(), eq(project), eq(MessageAction.CREATE), eq(user), eq(projectUser), eq("ADMIN"));
    assertThat(captor.getValue().getService()).isEqualTo(service);
  }
}