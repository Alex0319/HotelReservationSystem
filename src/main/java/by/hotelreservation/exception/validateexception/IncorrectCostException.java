package by.hotelreservation.exception.validateexception;

public class IncorrectCostException extends Exception {
    private static final long serialVersionUID = 1L;
    public IncorrectCostException(){
    }

    public IncorrectCostException(Exception e){
        super(e);
    }

    public IncorrectCostException(String message){
        super(message);
    }

    public IncorrectCostException(String message, Exception e){
        super(message, e);
    }
}
