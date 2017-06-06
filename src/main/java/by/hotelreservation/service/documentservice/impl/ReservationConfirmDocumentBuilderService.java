package by.hotelreservation.service.documentservice.impl;

import by.hotelreservation.bean.DocumentObject;
import by.hotelreservation.bean.entity.Reservation;
import by.hotelreservation.documentbuilder.DocumentBuilder;
import by.hotelreservation.documentbuilder.impl.ReservationConfirmDocumentBuilder;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.documentservice.DocumentBuilderService;
import by.hotelreservation.service.impl.ReservationServiceImpl;

import java.util.Map;

public class ReservationConfirmDocumentBuilderService implements DocumentBuilderService{
    public DocumentObject buildDocument(Map<String, String[]> documentParams) throws ServiceException {
        Reservation reservation = new ReservationServiceImpl().getById(Integer.parseInt(documentParams.get("id")[0]));
        if(reservation != null) {
            DocumentBuilder reservationConfirmDocumentBuilder = ReservationConfirmDocumentBuilder.getInstance();
            return reservationConfirmDocumentBuilder.buildDocument(reservation);
        }
        return null;
    }
}
