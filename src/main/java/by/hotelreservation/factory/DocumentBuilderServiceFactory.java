package by.hotelreservation.factory;

import by.hotelreservation.service.documentservice.DocumentBuilderService;

public interface DocumentBuilderServiceFactory {
    DocumentBuilderService getDocumentBuilderService(String documentName);
}
