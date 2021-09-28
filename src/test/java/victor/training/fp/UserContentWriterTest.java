package victor.training.fp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.StringWriter;
import java.io.Writer;

@RunWith(MockitoJUnitRunner.class)
public class UserContentWriterTest {
   @Mock
   private UserRepo userRepo;
   @InjectMocks
   private UserContentWriter contentWriter;

   @Test
   public void userContentWriter() {
//      Mockito.when(userRepo.findAll()).
      StringWriter writer = new StringWriter(); // B
      contentWriter.write(writer);
      String actual = writer.toString();
      Assert.assertEquals("username;firstname\n", actual);
   }
}