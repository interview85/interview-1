package com.company;

import java.io.*;

/**
 * Utility class used to manipulate files adn strings;
 * @author Andrew
 */
public class FileUtils {

    /**
     * Reads file char by char into a string
     * @param file a text file
     * @return string representation of a file
     */
    public String readFileToString(final File file) {
        return doReadFileToString(file, false);
    }

    /**
     * Reads file char by char, omitting unicode chars into a string
     * @param file a text file
     * @return string representation of a file
     */
    public String readFileToStringNoUnicode(final File file) {
        return doReadFileToString(file, true);
    }

    /**
     * Saves a string into a file.
     * @param string a string to be saved into the file
     * @param path pathway there to file must be located!
     * @return file containing the string
     */
    public File saveStringToFile(final String string, final String path) {
        if (string == null || path == null)
            throw new NullPointerException("String or path must not be null!");
        File file = new File(path);
        try (OutputStream o = new FileOutputStream(file)) {
            o.write(string.getBytes());
            return file;
        } catch (Exception e) {
            throw new RuntimeException("Failed to write string into file!", e);
        }
    }

    private String doReadFileToString(final File file, final boolean unicode) {
        if (file == null)
            throw new NullPointerException("File must not be null!");
        try (InputStream i = new FileInputStream(file)) {
            StringBuilder output = new StringBuilder();
            int data;
            while ((data = i.read()) > 0) {
                if (unicode) {
                    if (data < 0x80) { //128
                        output.append((char) data);
                        continue;
                    }
                } else {
                    output.append((char) data);
                }
            }
            return output.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read file into string!", e);
        }
    }

}

