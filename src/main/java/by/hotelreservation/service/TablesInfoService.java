package by.hotelreservation.service;

import by.hotelreservation.service.exception.ServiceException;

import java.util.List;

public interface TablesInfoService {
    List<String> getAllTablesNames() throws ServiceException;
}
