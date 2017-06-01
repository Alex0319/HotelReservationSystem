package by.hotelreservation.controller;

import by.hotelreservation.bean.entity.ReservationRoom;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.impl.ReservationRoomServiceImpl;
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
@RequestMapping(value = "/reservation_room")
public class ReservationRoomController {
    private static final Logger logger = LogManager.getLogger(ReservationRoomController.class.getName());

    @Autowired
    private ReservationRoomServiceImpl reservationRoomService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<ReservationRoom> getAll(){
        List<ReservationRoom> resultList = null;
        try {
            resultList = reservationRoomService.getAll();
        }catch (ServiceException e){
            logger.error(e);
        }
        return resultList;
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET, produces = "application/json")
    public ReservationRoom getById(@PathVariable int id){
        ReservationRoom reservationRoom = null;
/*        try {
            parkingSpace = parkingSpaceService.getEntity(id);
        }catch (ServiceException e){
            logger.error(e);
        }
 */       return reservationRoom;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Object add(HttpServletRequest req){
        Object result;
        try {
            result = reservationRoomService.add(reservationRoomService.build(req.getParameterMap()));
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
            reservationRoomService.update(reservationRoomService.build(req.getParameterMap()));
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
            reservationRoomService.delete(reservationRoomService.build(req.getParameterMap()));
        }catch (ServiceException e){
            logger.error(e);
            result = e.getMessage().substring(e.getMessage().lastIndexOf(":")+1);
        }
        return result;
    }
}