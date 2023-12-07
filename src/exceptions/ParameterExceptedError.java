package exceptions;

public class ParameterExceptedError extends Exception {
    public ParameterExceptedError(String paramName){
        super("parameter_excepted:"+paramName);
    }
}
