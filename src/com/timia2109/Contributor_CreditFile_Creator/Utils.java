package com.timia2109.Contributor_CreditFile_Creator;

import java.io.*;
import java.util.Scanner;

public class Utils {

    /**
     * Get a file as String
     * @param file The File
     * @return content of the file
     * @throws IOException Is it can't read
     */
    public static String getFile(File file) throws IOException{
        return getFile(new FileInputStream(file));
    }

    /**
     * Get the content from a given InputStream
     * @param is The InputStream
     * @return content of the InputStream
     * @throws IOException if it can't read
     */
    public static String getFile(InputStream is) throws IOException{
        Scanner sc = new Scanner(is);
        StringBuilder sb = new StringBuilder();

        while (sc.hasNextLine()) {
            sb.append(sc.nextLine()).append('\n');
        }

        sc.close();
        is.close();

        return sb.toString();
    }

    /**
     * Writes the String in the given file
     * @param content The content
     * @param outFile The File
     * @throws IOException If it can't write
     */
    public static void writeFile(String content, File outFile) throws IOException{
        FileWriter fw = new FileWriter(outFile);
        fw.write(content);
        fw.close();
    }


}
