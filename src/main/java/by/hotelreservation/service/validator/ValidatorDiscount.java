package by.hotelreservation.service.validator;

import by.hotelreservation.service.exception.IncorrectDiscountNameException;

import java.util.Map;

public class ValidatorDiscount extends AbstractValidator {
    public boolean validate(Map<String, String[]> data) throws IncorrectDiscountNameException {
        if (validateNameDiscount(data.get("name")[0])) {
            return true;
        }
        return false;
    }

    private boolean validateNameDiscount(String nameDiscount) throws IncorrectDiscountNameException {
        if (nameDiscount.length() < 45 & nameDiscount.length() > 3) {
            return true;
        }
        throw new IncorrectDiscountNameException("Incorrect discount name!");
    }
}
