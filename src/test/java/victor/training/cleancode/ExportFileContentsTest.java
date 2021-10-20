package victor.training.cleancode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import victor.training.cleancode.support.User;
import victor.training.cleancode.support.UserRepo;

import java.io.StringWriter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExportFileContentsTest {
   @Mock
   UserRepo userRepo;
   @InjectMocks
   ExportFileContents exportFileContents;

   @Test
   void userExportContent() {
      User user = new User().setUsername("jdoe").setEmail("a@b.com");
      when(userRepo.findAll()).thenReturn(List.of(user));
      StringWriter writer = new StringWriter();

      exportFileContents.userExportContent(writer);

      String actualData = writer.toString();

      assertThat(actualData).isEqualToIgnoringNewLines(
          "username;email\n" +
          "jdoe;a@b.com\n");
   }
}