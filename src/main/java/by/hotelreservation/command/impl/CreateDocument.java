package by.hotelreservation.command.impl;

import by.hotelreservation.bean.DocumentObject;
import by.hotelreservation.command.exception.CommandException;
import by.hotelreservation.factory.impl.DocumentBuilderMapper;
import by.hotelreservation.service.documentservice.DocumentBuilderService;
import by.hotelreservation.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CreateDocument   {
    public Object execute(HttpServletRequest req, HttpServletResponse response) throws CommandException {
        Map<String, String[]> requestParams = req.getParameterMap();
        DocumentObject documentObject;
        try {
            DocumentBuilderMapper documentBuilderMapper = DocumentBuilderMapper.getInstance();
            DocumentBuilderService documentBuilderService = documentBuilderMapper.getDocumentBuilderService(requestParams.get("docname")[0]);
            documentObject = documentBuilderService.buildDocument(requestParams);
            sendDocument(response, documentObject);
        }catch (ServiceException | IOException e){
            throw new CommandException(e);
        }
        return documentObject;
    }

    private void sendDocument(HttpServletResponse response, DocumentObject documentObject) throws IOException{
        if(documentObject != null){
            response.setContentType(documentObject.getMimeType());
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", documentObject.getDocumentName()));
            response.getOutputStream().write(documentObject.getDocumentBytes());
            response.getOutputStream().close();
        }
    }
}
