package by.hotelreservation.controller;

import by.hotelreservation.bean.dto.EntityDto;
import by.hotelreservation.bean.entity.Role;
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
@RequestMapping(value = "/role")
public class RoleController {
    private static final Logger logger = LogManager.getLogger(RoleController.class);

    @Autowired
    @Qualifier(value = "roleService")
    private CrudServiceExtended<Role> roleService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Role> getAll(){
        List<Role> resultList = null;
        try {
            resultList = (List<Role>) roleService.getAll();
        }catch (ServiceException e){
            logger.error(e);
        }
        return resultList;
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET, produces = "application/json")
    public Role getById(@PathVariable int id){
        Role role = null;
        try {
            role = (Role) roleService.getById(id);
        }catch (ServiceException e){
            logger.error(e);
        }
        return role;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Object add(HttpServletRequest req){
        Object result;
        try {
            result = roleService.add(roleService.build(req.getParameterMap()));
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
            roleService.update(roleService.build(req.getParameterMap()));
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
            roleService.delete(roleService.build(req.getParameterMap()));
        }catch (ServiceException e){
            logger.error(e);
            result = e.getMessage().substring(e.getMessage().lastIndexOf(":")+1);
        }
        return result;
    }

    @RequestMapping(value = "get_headers",method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<EntityDto> getAllHeaders(){
        List<EntityDto> resultList = null;
        try {
            resultList = roleService.getAllHeaders();
        }catch (ServiceException e){
            logger.error(e);
        }
        return resultList;
    }
}