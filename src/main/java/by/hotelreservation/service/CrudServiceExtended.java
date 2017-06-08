package by.hotelreservation.service;

import by.hotelreservation.bean.dto.EntityDto;
import by.hotelreservation.exception.ServiceException;

import java.util.List;

public interface CrudServiceExtended<T> extends CrudService<T> {
    List<EntityDto> getAllHeaders() throws ServiceException;
}
