package exceptions;

public class NotFound extends Exception{
    public NotFound(String item, String id){
        super("not_found:" + item + "/" + id);
    }
}
