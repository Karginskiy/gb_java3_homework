package lesson3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileUtils {

    public static void main(String[] args) {

        try {

            File file1 = new File("E:\\Projects\\gb_java3_homework\\src\\main\\resources\\text1.txt");
            File file2 = new File("E:\\Projects\\gb_java3_homework\\src\\main\\resources\\text2.txt");
            File file3 = new File("E:\\Projects\\gb_java3_homework\\src\\main\\resources\\pageFile");
            File merged = new File("E:\\Projects\\gb_java3_homework\\src\\main\\resources\\merged.txt");

            separator();
            System.out.println("First exercise -  File to Byte Array: \n");
            System.out.println(Arrays.toString(writeFileToArray(file1)));

            separator();
            System.out.println("Second exercise - Merge files (in example 2 files, but working with any quantity): \n");
            mergeFileArray(file1, file2);
            printFile(merged);

            separator();
            System.out.println("Third exercise - Gets the page from bigfile: \n");
            printPageFromFile(file3, 10);

        } catch (IOException e) {
            System.out.println("Houston! We have problems!");
        }

    }

    public static byte[] writeFileToArray(File file) throws IOException {

        InputStream stream = new FileInputStream(file);
        byte[] buffer = new byte[stream.available()];
        int mark = stream.read(buffer);

        stream.close();

        return buffer;

    }

    public static void mergeFileArray(File... files) throws IOException {

        ArrayList<FileInputStream> streams = new ArrayList<>();
        File mergedFile = new File("E:\\Projects\\gb_java3_homework\\src\\main\\resources\\merged.txt");
        mergedFile.createNewFile();
        FileOutputStream mergedOutputStream = new FileOutputStream(mergedFile);

        for (File file : files) {
            streams.add(new FileInputStream(file));
        }

        SequenceInputStream sequence =
                new SequenceInputStream(Collections.enumeration(streams));

        int x;
        while ((x = sequence.read()) != -1) {
            mergedOutputStream.write(x);
        }

        sequence.close();
        mergedOutputStream.close();

    }

    public static void printPageFromFile(File file, int pageIndex) throws IOException {

        if (pageIndex == 0) {
            System.out.println("Have you ever seen the page number 0? Good luck next time.");
        }

        StringBuilder page = new StringBuilder();
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.skipBytes(1800 * (pageIndex - 1));
        byte[] bytes = new byte[1800];
        randomAccessFile.read(bytes);

        for (byte bt : bytes) {
            page.append((char) bt);
        }

        randomAccessFile.close();

        System.out.printf("Page %d: \n\n", pageIndex);
        System.out.println(page.toString());

    }

    public static void printFile(File file) throws IOException {
        List<String> list = Files.readAllLines(Paths.get(file.getAbsolutePath()));
        list.forEach(System.out::println);
    }

    public static void separator() {
        System.out.println("====================================");
    }


}
