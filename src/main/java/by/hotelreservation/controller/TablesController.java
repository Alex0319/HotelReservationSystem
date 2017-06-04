package by.hotelreservation.controller;

import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.TablesInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/tables_info")
public class TablesController {
    private static final Logger logger = LogManager.getLogger(TablesInfoService.class);

    @Autowired
    private TablesInfoService tablesInfoService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ModelAndView getTableNames(){
        List<String> resultList = null;
        try {
            resultList = tablesInfoService.getAllTablesNames();
        }catch (ServiceException e){
            logger.error(e);
        }
        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("names", resultList);
        return modelAndView;
    }
}
