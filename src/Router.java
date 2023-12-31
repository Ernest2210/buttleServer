import java.lang.reflect.Method;

public class Router {
    public static void route(String message, Connection connection){
        try {
            if(message == null){
                return;
            }
            String messageType = message.split("&")[0];
            String messageBody = "";
            if (message.split("&").length == 2){
                messageBody = message.split("&")[1];
            }

            Method method = Views.class.getMethod(messageType, Connection.class, String.class);
            method.invoke(Views.class, connection, messageBody);
        } catch (Exception e){
            e.printStackTrace();
            connection.sendMessage("Exception-" + e.getMessage());
        }
    }
}
