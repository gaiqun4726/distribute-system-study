import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.*;

public class TestSerialize {
    public static final String sentence = "I love china. Seem that the long string is needed";

    public static void main(String[] args) {
        TestSerialize testSerialize = new TestSerialize();
        Person person = new Person("gaiqun", 18);
        testSerialize.hessianSerialize(person);
        testSerialize.javaSerialize(person);
    }

    public void hessianSerialize(Object obj) {  // hessian should be faster then java built in.
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            long start = System.currentTimeMillis();
            HessianOutput out = new HessianOutput(os);
            out.writeObject(obj);
            byte[] bytes = os.toByteArray();
            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            HessianInput in = new HessianInput(is);
            Person person = (Person) in.readObject();
            System.out.println(person.name);
            long end = System.currentTimeMillis();
            System.out.println("hessian take millis: " + (end - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void javaSerialize(Object obj) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            long start = System.currentTimeMillis();
            ObjectOutputStream out = new ObjectOutputStream(os);
            out.writeObject(obj);
            byte[] bytes = os.toByteArray();
            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            ObjectInputStream in = new ObjectInputStream(is);
            Person person = (Person) in.readObject();
            System.out.println(person.age);
            long end = System.currentTimeMillis();
            System.out.println("java take millis: " + (end - start));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class Person implements Serializable {  // need implement Serializable interface
        private transient String name; // transient variable won't be serialized
        private int age;

        public Person(String name, int age) {
            this.age = age;
            this.name = name;
        }
    }
}
