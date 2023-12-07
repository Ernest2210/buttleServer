import exceptions.ParameterExceptedError;

import java.util.Map;
import java.util.UUID;

public class Views {
    public static void createRoom(Connection connection, String message) throws Exception {
        UUID roomId = Server.createRoom(connection);

        String answer = "waitSecondPlayer&room=" + roomId.toString() + "\n";
        connection.sendMessage(answer);
    }

    public static void connectToRoom(Connection connection, String message) throws Exception {
        Map<String, String> body = Utils.getRequestBody(message);

        if(body.get("room") == null){
            throw new ParameterExceptedError("room");
        }

        UUID roomID = UUID.fromString(body.get("room"));
        Server.AddSecondPlayerIntoRoom(roomID, connection);

        String answer = "startGame&\n";
        for(Connection conn: Server.getConnectionsFromRoom(roomID)){
            conn.sendMessage(answer);
        }
    }
}
