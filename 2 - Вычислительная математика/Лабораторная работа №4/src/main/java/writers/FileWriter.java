package writers;

import java.io.IOException;

public class FileWriter implements Writer {
    private String filePath;
    private java.io.FileWriter fileWriter;

    public FileWriter(String filePath) throws IOException {
        this.filePath = filePath;
        this.fileWriter = new java.io.FileWriter(filePath, true);
    }

    @Override
    public void write(String text) throws IOException {
        fileWriter.write(text + '\n');
        fileWriter.flush();
    }
}
