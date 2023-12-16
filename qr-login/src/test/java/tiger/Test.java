package tiger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Author Zenghu
 * @Date 2022年12月26日 20:43
 * @Description
 * @Version: 1.0
 **/
public class Test {

    public static void main(String[] args) {
        try {
            RandomAccessFile accessFile = new RandomAccessFile("", "r");
            String s = accessFile.readLine();
            long filePointer = accessFile.getFilePointer();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
