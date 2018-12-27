package jdk;

public class TestInterrupt {
    private static class MyThread1 extends Thread {
        @Override
        public void run() {
//            try {
//                Thread.sleep(2000);
//                System.out.println("Thread run");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            while (!interrupted()) {
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new MyThread1();
        thread1.start();
        thread1.interrupt();
        System.out.println("Main run");
    }
}
