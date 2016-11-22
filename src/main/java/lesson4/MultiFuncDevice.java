package lesson4;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiFuncDevice {

    private final Lock scanLock = new ReentrantLock();
    private final Lock printLock = new ReentrantLock();
    private int scanCount = 0;
    private final Queue<File> printQueue = new ArrayBlockingQueue<>(100);
    private final Logger logger = Logger.getLogger(MultiFuncDevice.class);
    private static final ExecutorService printService = Executors.newSingleThreadExecutor();
    private static final ExecutorService scanService = Executors.newSingleThreadExecutor();

    private MultiFuncDevice() {
        printThread();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println("Problem with " + t.getName());
            e.printStackTrace();
        });

        MultiFuncDevice device = new MultiFuncDevice();

        device.print(new File("E:\\Projects\\gb_java3_homework\\src\\main\\resources\\text1.txt"));
        device.scan("1.txt");
        device.print(new File("E:\\Projects\\gb_java3_homework\\src\\main\\resources\\text2.txt"));
        device.scan("2.txt");
        device.print(new File("E:\\Projects\\gb_java3_homework\\src\\main\\resources\\text3.txt"));
        device.scan("3.txt");
        device.print(new File("E:\\Projects\\gb_java3_homework\\src\\main\\resources\\text4.txt"));
        device.print(new File("E:\\Projects\\gb_java3_homework\\src\\main\\resources\\text5.txt"));
        device.print(new File("E:\\Projects\\gb_java3_homework\\src\\main\\resources\\text6.txt"));
        device.scan("4.txt");
        device.scan("5.txt");

        printService.shutdown();
        scanService.shutdown();

    }

    public void scan(String toFile) {
        scanLock.lock();
        scanService.submit(() -> {
            int PAGE_COUNT = 3;
            File file = new File(toFile);
            try (PrintStream stream = new PrintStream(file)) {
                Thread.sleep(50);
                logger.info(String.format("Scanned %d pages ", scanCount++));
                logger.info(String.format("Scanning to %s", file.getName()));
                stream.append(pasteLorenIpsum());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Scan Thread");
        scanLock.unlock();
    }

    private void printThread() {
        printLock.lock();
        printService.submit(() -> {
            while (!printQueue.isEmpty()) {
                int PAGE_COUNT = 5;
                File fileFromQueue = printQueue.remove();
                logger.info(String.format("Printing - %s, %d in a queue. ", fileFromQueue.getName(), printQueue.size()));
                try (BufferedReader reader = new BufferedReader(new FileReader(fileFromQueue))) {
                    for (int i = 0; i < PAGE_COUNT; i++) {
                        Thread.sleep(50);
                        logger.info(String.format("Printed - %d pages", i + 1));
                    }
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "PrintThread");
        printLock.unlock();
    }

    public void print(File file) {
        printQueue.add(file);
    }


    private String pasteLorenIpsum() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam consequat efficitur elit, et dapibus " +
                "leo tincidunt sit amet. Integer eget interdum velit. Vivamus eleifend, risus pretium " +
                "aliquam rutrum, felis ligula ornare dolor, in scelerisque quam urna a arcu. Integer tempor " +
                "auctor augue ac semper. Etiam sed erat pharetra, pharetra nisi eu, scelerisque dolor. Donec " +
                "ut convallis erat. Vestibulum pharetra lacinia erat non dapibus. Aenean id mattis tellus, " +
                "sed pulvinar lorem. Praesent congue vel nisi quis sagittis.";
    }

}
