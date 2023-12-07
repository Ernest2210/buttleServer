import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Utils {
    public static Map<String, String> getRequestBody(String message){
        Map<String, String> body = new HashMap<>();
        String[] params = message.split(";");
        for (String param: params){
            body.put(param.split("=")[0], param.split("=")[1]);
        }
        return body;
    }
}
