package by.hotelreservation.service.documentservice.impl;

import by.hotelreservation.bean.DocumentObject;
import by.hotelreservation.bean.entity.ReservationRoom;
import by.hotelreservation.documentbuilder.DocumentBuilder;
import by.hotelreservation.documentbuilder.impl.ReservationRoomReportBuilder;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.documentservice.DocumentBuilderService;
import by.hotelreservation.service.impl.ReservationRoomServiceImpl;

import java.util.List;
import java.util.Map;

public class ReservationReportBuilderService implements DocumentBuilderService{
    @Override
    public DocumentObject buildDocument(Map<String, String[]> documentInfo) throws ServiceException {
        List<ReservationRoom> reservationRoomList = new ReservationRoomServiceImpl().getReservationRoomByUser(Integer.parseInt(documentInfo.get("id")[0]));
        if(reservationRoomList != null){
            DocumentBuilder documentBuilder = ReservationRoomReportBuilder.getInstance();
            return documentBuilder.buildDocument(reservationRoomList);
        }
        return null;
    }
}
