package by.hotelreservation.documentbuilder.impl;

import by.hotelreservation.bean.entity.Room;
import by.hotelreservation.documentbuilder.PdfDocumentBuilder;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

import java.io.IOException;

public class RoomDocumentBuilder extends PdfDocumentBuilder<Room>{
    private RoomDocumentBuilder(){
        super("/documents/room_blank.pdf", "Room.pdf");
    }

    private static class Holder{
        private final static RoomDocumentBuilder INSTANCE = new RoomDocumentBuilder();
    }

    public static RoomDocumentBuilder getInstance(){
        return Holder.INSTANCE;
    }


    @Override
    protected void setFields(AcroFields form, Room documentData) throws DocumentException{
        try {
            form.setField("name", documentData.getName());
            form.setField("floor", Integer.toString(documentData.getFloor()));
            form.setField("phone_number", documentData.getPhone());
        }catch (IOException e){
            throw new DocumentException(e);
        }
    }
}
