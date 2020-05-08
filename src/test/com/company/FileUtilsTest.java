package com.company;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileUtilsTest {

    private File file;

    @Before
    public void setUp() throws Exception {
        file = new File("src/test/resources/text.txt");
    }

    @Test
    public void readFileToStringNoUnicode() {
        FileUtils fu = new FileUtils();
        assertEquals("Resulting string is not as expected", "Hello,", fu.readFileToStringNoUnicode(this.file));
    }

    @Test
    public void readFileToString() {
        FileUtils fu = new FileUtils();
        assertEquals("Resulting string is not as expected", "Hello,学中文", fu.readFileToString(this.file));
    }

    @Test
    public void saveStringToFile() {
        FileUtils fu = new FileUtils();
        File file = fu.saveStringToFile("Hello, world!", "src/test/resources/newText.txt");
        assertTrue("File has not been created!", file.exists());
    }
}