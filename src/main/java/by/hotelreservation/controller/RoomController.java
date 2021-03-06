package by.hotelreservation.controller;

import by.hotelreservation.bean.dto.EntityDto;
import by.hotelreservation.bean.entity.Room;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.CrudServiceExtended;
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
@RequestMapping(value = "/room")
public class RoomController {
    private static final Logger logger = LogManager.getLogger(RoomController.class);

    @Autowired
    @Qualifier(value = "roomService")
    private CrudServiceExtended<Room> roomService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Room> getAll(){
        List<Room> resultList = null;
        try {
            resultList = (List<Room>) roomService.getAll();
        }catch (ServiceException e){
            logger.error(e);
        }
        return resultList;
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET, produces = "application/json")
    public Room getById(@PathVariable int id){
        Room room = null;
        try {
            room = (Room) roomService.getById(id);
        }catch (ServiceException e){
            logger.error(e);
        }
        return room;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Object add(HttpServletRequest req){
        Object result;
        try {
            result = roomService.add(roomService.build(req.getParameterMap()));
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
            roomService.update(roomService.build(req.getParameterMap()));
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
            roomService.delete(roomService.build(req.getParameterMap()));
        }catch (ServiceException e){
            logger.error(e);
            result = e.getMessage().substring(e.getMessage().lastIndexOf(":")+1);
        }
        return result;
    }

    @RequestMapping(value = "/get_headers",method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<EntityDto> getAllHeaders(){
        List<EntityDto> resultList = null;
        try {
            resultList = roomService.getAllHeaders();
        }catch (ServiceException e){
            logger.error(e);
        }
        return resultList;
    }
}