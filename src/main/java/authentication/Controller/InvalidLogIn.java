package authentication.Controller;

public class InvalidLogIn extends Exception{
    public InvalidLogIn(String message){
        super(message);
    }
}
