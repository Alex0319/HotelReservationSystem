package by.hotelreservation.controller;

import by.hotelreservation.bean.DocumentObject;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.factory.impl.DocumentBuilderMapper;
import by.hotelreservation.service.documentservice.DocumentBuilderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class DocumentController {
    private static final Logger logger = LogManager.getLogger(DocumentController.class);

    @RequestMapping(value = "/create_doc", method = RequestMethod.GET)
    public Object execute(HttpServletRequest req, HttpServletResponse response){
        Map<String, String[]> requestParams = req.getParameterMap();
        DocumentObject documentObject = null;
        try {
            DocumentBuilderMapper documentBuilderMapper = DocumentBuilderMapper.getInstance();
            DocumentBuilderService documentBuilderService = documentBuilderMapper.getDocumentBuilderService(requestParams.get("docname")[0]);
            documentObject = documentBuilderService.buildDocument(requestParams);
            sendDocument(response, documentObject);
        }catch (ServiceException | IOException e){
            logger.error(e);
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
