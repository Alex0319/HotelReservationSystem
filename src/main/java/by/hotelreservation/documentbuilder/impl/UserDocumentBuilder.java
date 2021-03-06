package by.hotelreservation.documentbuilder.impl;

import by.hotelreservation.bean.dto.UserDto;
import by.hotelreservation.documentbuilder.PdfDocumentBuilder;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

import java.io.IOException;

public class UserDocumentBuilder extends PdfDocumentBuilder<UserDto>{
    private UserDocumentBuilder(){
        super("/documents/user_blank.pdf", "User.pdf");
    }

    private static class Holder{
        private final static UserDocumentBuilder INSTANCE = new UserDocumentBuilder();
    }

    public static UserDocumentBuilder getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    protected void setFields(AcroFields form, UserDto documentData) throws DocumentException{
        try {
            form.setField("name", documentData.getName());
            form.setField("surname", documentData.getSurname());
            form.setField("email", documentData.getEmail());
            form.setField("mobile_phone", documentData.getMobilePhone());
            form.setField("login", documentData.getLogin());
        }catch (IOException e){
            throw new DocumentException(e);
        }
    }
}
