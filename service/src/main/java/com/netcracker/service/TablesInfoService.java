package com.netcracker.service;

import com.netcracker.service.exception.ServiceException;

import java.util.List;

public interface TablesInfoService {
    List<String> getAllTablesNames() throws ServiceException;
}
