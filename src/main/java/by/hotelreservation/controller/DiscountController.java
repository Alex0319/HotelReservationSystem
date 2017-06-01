package by.hotelreservation.controller;

import by.hotelreservation.bean.entity.Discount;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.impl.DiscountServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/discount")
public class DiscountController {
    private static final Logger logger = LogManager.getLogger(DiscountController.class.getName());

    @Autowired
    private DiscountServiceImpl discountService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Discount> getAll(){
        List<Discount> resultList = null;
        try {
            resultList = discountService.getAll();
        }catch (ServiceException e){
            logger.error(e);
        }
        return resultList;
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET, produces = "application/json")
    public Discount getById(@PathVariable int id){
        Discount discount = null;
/*        try {
            parkingSpace = parkingSpaceService.getEntity(id);
        }catch (ServiceException e){
            logger.error(e);
        }
 */       return discount;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Object add(HttpServletRequest req){
        Object result;
        try {
            result = discountService.add(discountService.build(req.getParameterMap()));
        }catch (ServiceException e){
            logger.error(e);
            result = e.getMessage().substring(e.getMessage().lastIndexOf(":")+1);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/plain; charset=UTF-8")
    public String update(HttpServletRequest req){
        String result = null;
        try {
            discountService.update(discountService.build(req.getParameterMap()));
        }catch (ServiceException e){
            logger.error(e);
            result = e.getMessage().substring(e.getMessage().lastIndexOf(":")+1);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "text/plain; charset=UTF-8")
    public String delete(HttpServletRequest req){
        String result = null;
        try {
            discountService.delete(discountService.build(req.getParameterMap()));
        }catch (ServiceException e){
            logger.error(e);
            result = e.getMessage().substring(e.getMessage().lastIndexOf(":")+1);
        }
        return result;
    }
}