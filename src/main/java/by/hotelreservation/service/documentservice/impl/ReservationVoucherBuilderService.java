package by.hotelreservation.service.documentservice.impl;

import by.hotelreservation.bean.DocumentObject;
import by.hotelreservation.bean.entity.Reservation;
import by.hotelreservation.documentbuilder.DocumentBuilder;
import by.hotelreservation.documentbuilder.impl.ReservationVoucherDocumentBuilder;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.documentservice.DocumentBuilderService;
import by.hotelreservation.service.impl.ReservationServiceImpl;

import java.util.Map;

public class ReservationVoucherBuilderService implements DocumentBuilderService{
    @Override
    public DocumentObject buildDocument(Map<String, String[]> documentInfo) throws ServiceException {
        Reservation reservation = new ReservationServiceImpl().getById(Integer.parseInt(documentInfo.get("id")[0]));
        if(reservation != null){
            DocumentBuilder documentBuilder = ReservationVoucherDocumentBuilder.getInstance();
            return documentBuilder.buildDocument(reservation);
        }
        return null;
    }
}
