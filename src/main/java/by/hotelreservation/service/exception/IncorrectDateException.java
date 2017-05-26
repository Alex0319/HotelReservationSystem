package by.hotelreservation.service.exception;

public class IncorrectDateException extends Exception {
    private static final long serialVersionUID = 1L;
    public IncorrectDateException(){
    }

    public IncorrectDateException(Exception e){
        super(e);
    }

    public IncorrectDateException(String message){
        super(message);
    }

    public IncorrectDateException(String message, Exception e){
        super(message, e);
    }
}
