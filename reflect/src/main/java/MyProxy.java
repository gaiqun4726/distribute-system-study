import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy implements InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(Object.class.equals(method.getDeclaringClass()));
        return "hello world";
    }

    public static Object getProxy(Class<?> clz) {
        return Proxy.newProxyInstance(clz.getClassLoader(), new Class[] {clz}, new MyProxy());
    }
}
