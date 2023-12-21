import exceptions.ParameterExceptedError;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Views {
    public static void createRoom(Connection connection, String message) throws Exception {
        UUID roomId = Server.createRoom(connection);

        String answer = "waitSecondPlayer&room=" + roomId.toString();
        connection.sendMessage(answer);
    }

    public static void connectToRoom(Connection connection, String message) throws Exception {
        Map<String, String> body = Utils.getRequestBody(message);

        if(body.get("room") == null){
            throw new ParameterExceptedError("room");
        }

        UUID roomID = UUID.fromString(body.get("room"));
        Server.addSecondPlayerIntoRoom(roomID, connection);

        String answer = "startGame&";
        for(Connection conn: Server.getConnectionsFromRoom(roomID)){
            conn.sendMessage(answer);
        }
    }

    public static void makeMove(Connection connection, String message) throws Exception {
        Map<String, String> body = Utils.getRequestBody(message);

        if(body.get("x_coord") == null){
            throw new ParameterExceptedError("x_coord");
        }
        if(body.get("y_coord") == null){
            throw new ParameterExceptedError("y_coord");
        }
        if(body.get("direction") == null){
            throw new ParameterExceptedError("direction");
        }

        Server.getAnotherConnectionFromRoom(connection.getRoomId(), connection).sendMessage("makeMove&"+message);
    }

    public static void makeShoot(Connection connection, String message) throws Exception{
        Map<String, String> body = Utils.getRequestBody(message);

        if(body.get("x_coord") == null){
            throw new ParameterExceptedError("x_coord");
        }
        if(body.get("y_coord") == null){
            throw new ParameterExceptedError("y_coord");
        }
        if(body.get("direction") == null){
            throw new ParameterExceptedError("direction");
        }

        Server.getAnotherConnectionFromRoom(connection.getRoomId(), connection).sendMessage("makeShoot&"+message);
    }

    public static void gameOver(Connection connection, String message) throws Exception{
        Map<String, String> body = Utils.getRequestBody(message);

        if(body.get("lose_type") == null){
            throw new ParameterExceptedError("lose_type");
        }

        List<Connection> connections = Server.getConnectionsFromRoom(connection.getRoomId());
        if(connections != null) {
            Server.deleteRoom(connection.getRoomId());
            for (Connection conn : connections) {
                conn.sendMessage("gameOver&" + message);
                conn.setRoomId(null);
            }
        }
    }
}
