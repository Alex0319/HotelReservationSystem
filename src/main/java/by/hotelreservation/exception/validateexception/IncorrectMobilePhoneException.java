package by.hotelreservation.exception.validateexception;

public class IncorrectMobilePhoneException extends Exception{
    private static final long serialVersionUID = 1L;
    public IncorrectMobilePhoneException(){
    }

    public IncorrectMobilePhoneException(Exception e){
        super(e);
    }

    public IncorrectMobilePhoneException(String message){
        super(message);
    }

    public IncorrectMobilePhoneException(String message, Exception e){
        super(message, e);
    }
}
