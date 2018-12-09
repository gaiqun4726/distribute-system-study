public class TestInherit {
    static class Super {
        public static int val = 1;
        public static final String HELLOWORLD = "hello world";
        static {
            System.out.println("Super init");
        }
    }

    interface Super2 {
        int val2 = 2;
    }

    static class Sub extends Super implements Super2 {
        static {
            System.out.println("Sub init");
        }
    }

    public static void main(String[] args) {
//        System.out.println(Sub.val);
//        System.out.println(Sub.val2);
//        Super[] supers = new Super[10];
        System.out.println(Super.HELLOWORLD);
    }
}
