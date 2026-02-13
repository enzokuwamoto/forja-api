package project.forja.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("ID não encontrado.");
    }

    public UserNotFoundException(String message){
        super(message);
    }
}