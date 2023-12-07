import java.util.UUID;

public class Views {
    public static void createRoom(Connection connection, String message) throws Exception {
        UUID roomId = Server.createRoom(connection);

        String answer = "waitSecondPlayer&room=" + roomId.toString() + "\n";
        connection.sendMessage(answer);
    }
}
