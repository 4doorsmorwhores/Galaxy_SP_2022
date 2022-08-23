/**
 * class Reader
 * @package Datas
 * @author Andrei Akhramchuk
 * @version 1.0;
 */
package Datas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    /**
     * This class will read datas from .csv format and will convert it to Array of Strings
     * @param filePath
     * @return Array of read strings;
     * @throws IOException ( if path is empty )
     */
    public static String[] OpenAndRead(String filePath) throws IOException
    {
        FileReader x = new FileReader(filePath);
        BufferedReader varRead = new BufferedReader(x);

        int num = numStrings(filePath);;
        String[] lines = new String[num];

        int i;
        for (i = 0; i < num; i++)
        {
            lines[i] = varRead.readLine();
        }

        varRead.close();
        return lines;
    }

    /**
     * This class will count length of our file
     * @param filePath
     * @return file length
     * @throws IOException
     */
    static int numStrings(String filePath) throws IOException
    {

        FileReader text = new FileReader(filePath);
        BufferedReader bReader = new BufferedReader(text);

        String str;
        int num = 0;

        while ((str = bReader.readLine()) != null)
        {
            num++;
        }
        bReader.close();

        return num;
    }

}