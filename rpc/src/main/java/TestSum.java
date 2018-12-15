import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class TestSum {
    public static int value = 0;
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static LongAdder longAdder = new LongAdder();
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(100, () -> {
//            System.out.println(value);
//        System.out.println(atomicInteger.get());
        System.out.println(longAdder.intValue());
    });

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Thread1());
        }
        executorService.shutdown();
    }

    static class Thread1 implements Runnable {

        public void run() {
//            value++;
//            atomicInteger.incrementAndGet();
            longAdder.increment();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
