package by.hotelreservation.documentbuilder;

import by.hotelreservation.bean.DocumentObject;
import by.hotelreservation.exception.ServiceException;

public interface DocumentBuilder<T> {
    DocumentObject buildDocument(T documentData) throws ServiceException;
}
