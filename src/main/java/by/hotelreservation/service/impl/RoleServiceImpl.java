package by.hotelreservation.service.impl;

import by.hotelreservation.bean.entity.Role;
import by.hotelreservation.builder.RoleBuilder;
import by.hotelreservation.dao.RoleDao;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.exception.validateexception.IncorrectNameRoleException;
import by.hotelreservation.exception.validateexception.IncorrectRightRoleException;
import by.hotelreservation.service.AbstractService;
import by.hotelreservation.service.CrudServiceExtended;
import by.hotelreservation.service.validator.ValidatorRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends AbstractService implements CrudServiceExtended<Role> {
    @Autowired
    private RoleDao roleDao;

    public List<String> getAllHeaders() throws ServiceException {
        Connection connection = null;
        try{
            connection = getConnection();
            return roleDao.getRoleHeaders(connection);
        }catch (DAOException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public List<Role> getAll() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return roleDao.getRoles(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public List<Role> add(Role entity) throws ServiceException {
        Connection connection = null;
        List<Role> roles;
        try {
            connection = getConnection();
            roleDao.addRole(entity,connection);
            roles = roleDao.getRoles(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
        return roles;
    }

    public void delete(Role entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            roleDao.removeRole(entity,connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public void update(Role entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            roleDao.updateRole(entity,connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    @Override
    public Role getById(int id) throws ServiceException {
        return null;
    }

    public Role build(Map<String, String[]> params) throws ServiceException {
        ValidatorRole validatorRole = new ValidatorRole();
        try {
            if (validatorRole.validate(params)) {
                return new RoleBuilder().id(Integer.parseInt(params.get("id")[0]))
                        .nameRole(params.get("nameRole")[0])
                        .update(Byte.parseByte(params.get("update")[0]))
                        .delete(Byte.parseByte(params.get("delete")[0]))
                        .insert(Byte.parseByte(params.get("insert")[0]))
                        .create(Byte.parseByte(params.get("create")[0]))
                        .select(Byte.parseByte(params.get("select")[0]))
                        .drop(Byte.parseByte(params.get("drop")[0]))
                        .grant(Byte.parseByte(params.get("grant")[0]))
                        .build();
            }
        }catch (IncorrectNameRoleException | IncorrectRightRoleException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public Role getLastInsertedEntity() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return roleDao.getLastInsertedRole(connection);
        }catch (DAOException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }


}