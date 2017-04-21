package com.netcracker.service.impl;

import com.netcracker.dao.RoleDao;
import com.netcracker.dao.bean.Role;
import com.netcracker.dao.builder.RoleBuilder;
import com.netcracker.dao.exception.DaoException;
import com.netcracker.dao.impl.RoleDaoImpl;
import com.netcracker.service.AbstractService;
import com.netcracker.service.CrudServiceExtended;
import com.netcracker.service.exception.ServiceException;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class RoleServiceImpl extends AbstractService implements CrudServiceExtended<Role> {
    private RoleDao roleDao = new RoleDaoImpl();

    public List<String> getAllHeaders() throws ServiceException {
        Connection connection = null;
        try{
            connection = getConnection();
            return roleDao.getRoleHeaders(connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public List<Role> getAllEntities() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return roleDao.getRoles(connection);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public List<Role> addEntity(Role entity) throws ServiceException {
        Connection connection = null;
        List<Role> roles;
        try {
            connection = getConnection();
            roleDao.addRole(entity,connection);
            roles = roleDao.getRoles(connection);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
        return roles;
    }

    public void removeEntity(Role entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            roleDao.removeRole(entity,connection);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public void updateEntity(Role entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            roleDao.updateRole(entity,connection);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public Role buildEntity(Map<String, String[]> params) throws ServiceException {
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
}
