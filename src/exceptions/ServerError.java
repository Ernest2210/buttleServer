package exceptions;

public class ServerError extends Exception{
    public ServerError(){
        super("server_error:");
    }
}
