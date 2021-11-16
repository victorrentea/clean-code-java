package victor.training.fp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class FileExporterTest {

   @Test
   void exportFile() throws IOException {
      FileExporter exporter = new FileExporter();

      exporter.exportFile(writer -> writer.write("X"),"out.dat");

      assertThat(new File("target/out.dat")).exists();
      String readFile = null;// toDO
      assertThat(readFile).isEqualTo("X");
   }
   @Test
   void exportFileError() throws IOException {
      FileExporter exporter = new FileExporter();

      assertThatThrownBy(() -> exporter.exportFile(writer -> {
         throw new RuntimeException("intentional");
      },"out.dat"));
      //check email is sent (eg)
   }
}