package by.hotelreservation.service.impl;

import by.hotelreservation.bean.dto.EntityDto;
import by.hotelreservation.bean.entity.Role;
import by.hotelreservation.builder.RoleBuilder;
import by.hotelreservation.dao.EntityDao;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.exception.validateexception.IncorrectNameRoleException;
import by.hotelreservation.exception.validateexception.IncorrectRightRoleException;
import by.hotelreservation.service.CrudServiceExtended;
import by.hotelreservation.service.validator.ValidatorRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("roleService")
public class RoleServiceImpl implements CrudServiceExtended<Role> {
    @Autowired
    private EntityDao<Role> roleDao;

    public List<EntityDto> getAllHeaders() throws ServiceException {
        List<EntityDto> resultList = new ArrayList<>();
        List<Role> roleList = getAll();
        for (Role role: roleList) {
            resultList.add(new EntityDto(role.getId(),role.getNameRole()));
        }
        return resultList;
    }

    public List<Role> getAll() throws ServiceException {
        try {
            return roleDao.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    public List<Role> add(Role entity) throws ServiceException {
        List<Role> roles;
        try {
            roleDao.add(entity);
            roles = roleDao.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return roles;
    }

    @Transactional
    public void delete(Role entity) throws ServiceException {
        try {
            roleDao.remove(entity.getId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    public void update(Role entity) throws ServiceException {
        try {
            roleDao.update(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Role getById(int id) throws ServiceException {
        try {
            return roleDao.getById(id);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
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
}