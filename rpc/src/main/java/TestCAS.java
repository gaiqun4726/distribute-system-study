import java.util.concurrent.atomic.AtomicInteger;

public class TestCAS {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(0);
        boolean isSuccess = integer.compareAndSet(0, 2);
        System.out.println(isSuccess);
        System.out.println(integer.get());
    }
}
