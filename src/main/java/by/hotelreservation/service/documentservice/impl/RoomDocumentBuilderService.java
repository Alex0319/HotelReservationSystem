package by.hotelreservation.service.documentservice.impl;

import by.hotelreservation.bean.DocumentObject;
import by.hotelreservation.bean.Room;
import by.hotelreservation.documentbuilder.DocumentBuilder;
import by.hotelreservation.documentbuilder.impl.RoomDocumentBuilder;
import by.hotelreservation.service.documentservice.DocumentBuilderService;
import by.hotelreservation.service.exception.ServiceException;
import by.hotelreservation.service.impl.RoomServiceImpl;

import java.util.Map;

public class RoomDocumentBuilderService implements DocumentBuilderService{
    @Override
    public DocumentObject buildDocument(Map<String, String[]> documentInfo) throws ServiceException {
        Room room = new RoomServiceImpl().getEntity(Integer.parseInt(documentInfo.get("id")[0]));
        if(room != null) {
            DocumentBuilder roomDocumentBuilder = RoomDocumentBuilder.getInstance();
            return roomDocumentBuilder.buildDocument(room);
        }
        return null;
    }
}
