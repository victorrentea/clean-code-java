package victor.training.cleancode.extra.inheritance;

class CsvWriter {

    public void writeLine() {
    }

    public void writeCell(String value) {
    }
}

class SomeOtherClass {
    public void writeFooter(CsvWriter writer) {
        writer.writeCell("Generated with X SoftWare");
        writer.writeLine();
    }
}

class AnotherDistantClass {
    private final CsvWriter csvWriterInterface;

    public AnotherDistantClass(CsvWriter csvWriterInterface) {
        this.csvWriterInterface = csvWriterInterface;
    }
    public void writeHeader() {
        csvWriterInterface.writeCell("Head1");
        csvWriterInterface.writeCell("Head2");
        csvWriterInterface.writeLine();
    }

}
