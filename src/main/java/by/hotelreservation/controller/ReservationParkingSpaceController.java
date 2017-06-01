package by.hotelreservation.controller;

import by.hotelreservation.bean.entity.ReservationParkingSpace;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.impl.ReservationParkingSpaceServiceImpl;
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
@RequestMapping(value = "/reservation_parking_space")
public class ReservationParkingSpaceController {
    private static final Logger logger = LogManager.getLogger(ReservationParkingSpaceController.class.getName());

    @Autowired
    private ReservationParkingSpaceServiceImpl reservationParkingSpaceService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<ReservationParkingSpace> getAll(){
        List<ReservationParkingSpace> resultList = null;
        try {
            resultList = reservationParkingSpaceService.getAll();
        }catch (ServiceException e){
            logger.error(e);
        }
        return resultList;
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET, produces = "application/json")
    public ReservationParkingSpace getById(@PathVariable int id){
        ReservationParkingSpace reservationParkingSpace = null;
/*        try {
            parkingSpace = parkingSpaceService.getEntity(id);
        }catch (ServiceException e){
            logger.error(e);
        }
 */       return reservationParkingSpace;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Object add(HttpServletRequest req){
        Object result;
        try {
            result = reservationParkingSpaceService.add(reservationParkingSpaceService.build(req.getParameterMap()));
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
            reservationParkingSpaceService.update(reservationParkingSpaceService.build(req.getParameterMap()));
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
            reservationParkingSpaceService.delete(reservationParkingSpaceService.build(req.getParameterMap()));
        }catch (ServiceException e){
            logger.error(e);
            result = e.getMessage().substring(e.getMessage().lastIndexOf(":")+1);
        }
        return result;
    }
}