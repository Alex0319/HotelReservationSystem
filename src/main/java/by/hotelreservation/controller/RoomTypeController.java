package by.hotelreservation.controller;

import by.hotelreservation.bean.entity.RoomType;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.CrudService;
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
@RequestMapping(value = "/room_type")
public class RoomTypeController {
    private static final Logger logger = LogManager.getLogger(RoomTypeController.class);

    @Autowired
    private CrudService<RoomType> roomTypeService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<RoomType> getAll(){
        List<RoomType> resultList = null;
        try {
            resultList = roomTypeService.getAll();
        }catch (ServiceException e){
            logger.error(e);
        }
        return resultList;
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET, produces = "application/json")
    public RoomType getById(@PathVariable int id){
        RoomType roomType = null;
        try {
            roomType = roomTypeService.getById(id);
        }catch (ServiceException e){
            logger.error(e);
        }
        return roomType;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Object add(HttpServletRequest req){
        Object result;
        try {
            result = roomTypeService.add(roomTypeService.build(req.getParameterMap()));
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
            roomTypeService.update(roomTypeService.build(req.getParameterMap()));
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
            roomTypeService.delete(roomTypeService.build(req.getParameterMap()));
        }catch (ServiceException e){
            logger.error(e);
            result = e.getMessage().substring(e.getMessage().lastIndexOf(":")+1);
        }
        return result;
    }
}