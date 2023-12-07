import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try {
            Server server = new Server(4000);
            server.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}