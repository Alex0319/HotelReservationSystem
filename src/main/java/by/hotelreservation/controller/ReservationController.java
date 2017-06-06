package by.hotelreservation.controller;

import by.hotelreservation.bean.entity.Reservation;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.CrudService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {
    private static final Logger logger = LogManager.getLogger(ReservationController.class);

    @Autowired
    @Qualifier(value = "reservation")
    private CrudService<Reservation> reservationService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Reservation> getAll(){
        List<Reservation> resultList = null;
        try {
            resultList = reservationService.getAll();
        }catch (ServiceException e){
            logger.error(e);
        }
        return resultList;
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET, produces = "application/json")
    public Reservation getById(@PathVariable int id){
        Reservation reservation = null;
        try {
            reservation = reservationService.getById(id);
        }catch (ServiceException e){
            logger.error(e);
        }
        return reservation;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Object add(HttpServletRequest req){
        Object result;
        try {
            result = reservationService.add(reservationService.build(req.getParameterMap()));
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
            reservationService.update(reservationService.build(req.getParameterMap()));
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
            reservationService.delete(reservationService.build(req.getParameterMap()));
        }catch (ServiceException e){
            logger.error(e);
            result = e.getMessage().substring(e.getMessage().lastIndexOf(":")+1);
        }
        return result;
    }
}