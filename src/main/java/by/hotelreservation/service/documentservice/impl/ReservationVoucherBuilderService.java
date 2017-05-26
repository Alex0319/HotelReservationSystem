package by.hotelreservation.service.documentservice.impl;

import by.hotelreservation.bean.DocumentObject;
import by.hotelreservation.bean.ReservationRoom;
import by.hotelreservation.documentbuilder.DocumentBuilder;
import by.hotelreservation.documentbuilder.impl.ReservationVoucherDocumentBuilder;
import by.hotelreservation.service.documentservice.DocumentBuilderService;
import by.hotelreservation.service.exception.ServiceException;
import by.hotelreservation.service.impl.ReservationRoomServiceImpl;

import java.util.List;
import java.util.Map;

public class ReservationVoucherBuilderService implements DocumentBuilderService{
    @Override
    public DocumentObject buildDocument(Map<String, String[]> documentInfo) throws ServiceException {
        List<ReservationRoom> reservationRooms = new ReservationRoomServiceImpl()
                .getReservationRoomByReservation(Integer.parseInt(documentInfo.get("id")[0]));
        if(reservationRooms != null){
            DocumentBuilder documentBuilder = ReservationVoucherDocumentBuilder.getInstance();
            return documentBuilder.buildDocument(reservationRooms);
        }
        return null;
    }
}
