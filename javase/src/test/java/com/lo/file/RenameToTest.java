package com.lo.file;

import java.io.File;

public class RenameToTest {
    public static void main(String[] args) {
        File file=new File("./test");
        file.renameTo(new File("./test2"));

    }
}
