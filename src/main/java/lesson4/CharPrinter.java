package lesson4;

public class CharPrinter {

    private final Object locker = new Object();
    private char flag = 'B';

    public static void main(String[] args) {

        new CharPrinter().printAB();

    }

    private void printA() {
        final char a = 'A';
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                synchronized (locker) {
                    while (flag == 'A') {
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(a);
                    flag = 'A';
                    locker.notifyAll();
                }
            }
        }).start();
    }

    private void printB() {
        final char b = 'B';
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                synchronized (locker) {
                    while (flag == 'B') {
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(b);
                    flag = 'B';
                    locker.notifyAll();
                }
            }
        }).start();
    }

    private void printAB() {
        printA();
        printB();
    }

}
