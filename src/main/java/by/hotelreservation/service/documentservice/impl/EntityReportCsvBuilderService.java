package by.hotelreservation.service.documentservice.impl;

import by.hotelreservation.bean.DocumentObject;
import by.hotelreservation.documentbuilder.CsvDocumentBuilder;
import by.hotelreservation.documentbuilder.DocumentBuilder;
import by.hotelreservation.service.CrudService;
import by.hotelreservation.service.CrudServiceMapper;
import by.hotelreservation.service.documentservice.DocumentBuilderService;
import by.hotelreservation.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public class EntityReportCsvBuilderService implements DocumentBuilderService{
    @Override
    public DocumentObject buildDocument(Map<String, String[]> documentInfo) throws ServiceException {
        CrudService service =  CrudServiceMapper.getService(documentInfo.get("entity")[0]);
        List<?> resultList = service.getAllEntities();
        DocumentBuilder<List<?>> documentBuilder = new CsvDocumentBuilder<>(documentInfo.get("entity")[0].concat(" report.csv"));
        return documentBuilder.buildDocument(resultList);
    }
}
