package by.hotelreservation.command.controller;

import by.hotelreservation.service.CrudService;
import by.hotelreservation.service.CrudServiceMapper;
import by.hotelreservation.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UpdateEntity  {
    private static final Logger logger = LogManager.getLogger(UpdateEntity.class.getName());

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String execute(HttpServletRequest req){
        String result = null;
        Map<String, String[]> requestParams = req.getParameterMap();
        try {
            CrudService service =  CrudServiceMapper.getService(requestParams.get("tableName")[0]);
            service.updateEntity(service.buildEntity(requestParams));
        }catch (ServiceException e){
            logger.error(e);
            result = e.getMessage().substring(e.getMessage().lastIndexOf(":")+1);
        }
        return result;
    }
}
