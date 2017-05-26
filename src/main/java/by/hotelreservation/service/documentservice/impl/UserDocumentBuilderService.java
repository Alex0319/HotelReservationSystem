package by.hotelreservation.service.documentservice.impl;

import by.hotelreservation.bean.DocumentObject;
import by.hotelreservation.bean.User;
import by.hotelreservation.documentbuilder.DocumentBuilder;
import by.hotelreservation.documentbuilder.impl.UserDocumentBuilder;
import by.hotelreservation.service.documentservice.DocumentBuilderService;
import by.hotelreservation.service.exception.ServiceException;
import by.hotelreservation.service.impl.UserServiceImpl;

import java.util.Map;

public class UserDocumentBuilderService implements DocumentBuilderService{
    @Override
    public DocumentObject buildDocument(Map<String, String[]> documentInfo) throws ServiceException {
        User user = new UserServiceImpl().getEntity(Integer.parseInt(documentInfo.get("id")[0]));
        if(user != null) {
            DocumentBuilder userDocumentBuilder = UserDocumentBuilder.getInstance();
            return userDocumentBuilder.buildDocument(user);
        }
        return null;
    }
}
