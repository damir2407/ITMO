import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private boolean fileMode;
    private String path;
    private FileWriter writer;


    public void write(String text) throws IOException {

        if (!isFileMode()) {
            System.out.println(text);
        } else {
            writer.write(text+'\n');
            writer.flush();
        }

    }

    public boolean isFileMode() {
        return fileMode;
    }

    public void setFileMode(boolean fileMode) {
        this.fileMode = fileMode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) throws IOException {
        this.path = path;
        this.writer = new FileWriter(getPath(), true);
    }
}
