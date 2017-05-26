package by.hotelreservation.command.controller;

import by.hotelreservation.service.TablesInfoService;
import by.hotelreservation.service.exception.ServiceException;
import by.hotelreservation.service.impl.TablesInfoServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GetTableNames  {
    private static final Logger logger = LogManager.getLogger(GetTableNames.class.getName());

    @ResponseBody
    @RequestMapping(value = "/admin_start", method = RequestMethod.GET, produces = "application/json")
    public ModelAndView execute(){
        List<String> resultList = null;
        try {
            TablesInfoService service = new TablesInfoServiceImpl();
            resultList = service.getAllTablesNames();
        }catch (ServiceException e){
            logger.error(e);
        }
        return new ModelAndView("admin", "names", resultList);
    }
}

