package victor.training.cleancode;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

class CsvWriterImplemForTests implements ICsvWriter { // NU  ia mana. nu extinzi clase concrete

    @Override
    public void writeLine() {
        //altceva bun in teste
    }

    @Override
    public void writeCell(String value) {
        // alta implementare
    }
}
class CsvWriter implements ICsvWriter {

    private final Writer file;

    public CsvWriter(File file) {
        try {
            this.file = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeLine() {

        // TODO lucreaza cu un fisier
    }

    @Override
    public void writeCell(String value) {
        // TODO lucreaza cu un fisier
    }
}

class SomeOtherClassTest {
    @Test
    public void test() throws IOException {
//        File file = File.createTempFile("data",".txt");
//        ICsvWriter csvWriter = new CsvWriter(file);
        CsvWriterImplemForTests csvWriter = new CsvWriterImplemForTests();
        SomeOtherClass some = new SomeOtherClass();
        some.writeFooter(csvWriter);

//        IO
    }
}

class SomeOtherClass {
    public void writeFooter(ICsvWriter writer) {
        writer.writeCell("Generated with X SoftWare");
        writer.writeLine();
    }
}

class AnotherDistantClass {
    private final ICsvWriter csvWriter;

    public AnotherDistantClass(ICsvWriter csvWriter) {
        this.csvWriter = csvWriter;
    }
    public void writeHeader() {
        csvWriter.writeCell("Head1");
        csvWriter.writeCell("Head2");
        csvWriter.writeLine();
    }

}
