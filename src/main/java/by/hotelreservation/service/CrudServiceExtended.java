package by.hotelreservation.service;

import by.hotelreservation.exception.ServiceException;

import java.util.List;

public interface CrudServiceExtended<T> extends CrudService<T> {
    List<String> getAllHeaders() throws ServiceException;
}
