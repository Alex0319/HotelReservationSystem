package by.hotelreservation.documentbuilder.impl;

import by.hotelreservation.bean.Reservation;
import by.hotelreservation.documentbuilder.PdfDocumentBuilder;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

import java.io.IOException;

public class ReservationConfirmDocumentBuilder extends PdfDocumentBuilder<Reservation> {
    private ReservationConfirmDocumentBuilder(){
        super("/documents/reservation_confirm_blank.pdf", "Reservation Confirm.pdf");
    }

    private static class Holder{
        private final static ReservationConfirmDocumentBuilder INSTANCE = new ReservationConfirmDocumentBuilder();
    }

    public static ReservationConfirmDocumentBuilder getInstance(){
        return Holder.INSTANCE;
    }

    protected void setFields(AcroFields form, Reservation reservation) throws DocumentException, IOException{
        form.setField("addressee", reservation.getUser().getUserFullname());
        form.setField("date", reservation.getDateIn().toString());
        form.setField("appeal", "Уважаемый гость " + reservation.getUser().getUserFullname());
        form.setField("date-in", reservation.getDateIn().toString());
        form.setField("date-out", reservation.getDateOut().toString());
    }
}
