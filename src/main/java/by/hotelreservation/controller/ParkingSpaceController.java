package by.hotelreservation.controller;

import by.hotelreservation.bean.entity.ParkingSpace;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.impl.ParkingSpaceServiceImpl;
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
@RequestMapping(value = "/parking_space")
public class ParkingSpaceController {
    private static final Logger logger = LogManager.getLogger(ParkingSpaceController.class.getName());

    @Autowired
    private ParkingSpaceServiceImpl parkingSpaceService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<ParkingSpace> getAll(){
        List<ParkingSpace> resultList = null;
        try {
            resultList = parkingSpaceService.getAll();
        }catch (ServiceException e){
            logger.error(e);
        }
        return resultList;
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET, produces = "application/json")
    public ParkingSpace getById(@PathVariable int id){
        ParkingSpace parkingSpace = null;
/*        try {
            parkingSpace = parkingSpaceService.getEntity(id);
        }catch (ServiceException e){
            logger.error(e);
        }
 */       return parkingSpace;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Object add(HttpServletRequest req){
        Object result;
        try {
            result = parkingSpaceService.add(parkingSpaceService.build(req.getParameterMap()));
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
            parkingSpaceService.update(parkingSpaceService.build(req.getParameterMap()));
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
            parkingSpaceService.delete(parkingSpaceService.build(req.getParameterMap()));
        }catch (ServiceException e){
            logger.error(e);
            result = e.getMessage().substring(e.getMessage().lastIndexOf(":")+1);
        }
        return result;
    }
}