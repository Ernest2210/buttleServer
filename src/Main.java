import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server(4000);
            server.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}