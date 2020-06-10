package victor.training.cleancode;

public interface ICsvWriter {
   void writeLine();

   void writeCell(String value);
}
