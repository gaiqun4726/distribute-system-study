public class Main {
    public static void main(String[] args) {
        IUser user = (IUser) MyProxy.getProxy(IUser.class);
        System.out.println(user.sayHello());
    }
}
