import exceptions.NotFound;
import exceptions.ServerError;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server{
    private ServerSocket serverSocket;
    private static Map<UUID, List<Connection>> rooms = new HashMap<>();

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        System.out.println("Server started");
    }

    public void run() throws IOException {
        while (true){
            Socket client = serverSocket.accept();
            new Connection(client);
        }
    }

    public static List<Connection> getConnectionsFromRoom(UUID id){
        return rooms.get(id);
    }

    public static Connection getAnotherConnectionFromRoom(UUID id, Connection connection) throws ServerError {
        List<Connection> connections = rooms.get(id);
        for(Connection conn: connections){
            if(conn != connection){
                return conn;
            }
        }
        throw new ServerError();
    }

    public static UUID createRoom(Connection connection){
        UUID id = UUID.randomUUID();
        ArrayList<Connection> connections = new ArrayList<>(2);
        connections.add(connection);
        connection.setRoomId(id);
        rooms.put(id, connections);
        return id;
    }

    public static void addSecondPlayerIntoRoom(UUID id, Connection connection) throws NotFound {
        List<Connection> connections = rooms.get(id);
        if(connections != null){
            connection.setRoomId(id);
            connections.add(connection);
        }else {
            throw new NotFound("room", id.toString());
        }
    }

    public static void deleteRoom(UUID id){
        System.out.println("ROOM " + id.toString() + " DELETED");
        rooms.remove(id);
    }
}
