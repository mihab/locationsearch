package locationsearch.dao.csv;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Class that writes a string to a file.
 * 
 * @author miha
 * 
 */
public class StringFileWriter {

    /**
     * Write contents of the the string to a file.
     * 
     * @param contents
     *            Contents to write.
     * @param fileName
     *            Filename to write to.
     */
    public void write(String contents, String fileName) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
            bw.write(contents);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
