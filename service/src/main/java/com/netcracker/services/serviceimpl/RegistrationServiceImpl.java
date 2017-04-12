package com.netcracker.services.serviceimpl;

import com.netcracker.dao.bean.User;
import com.netcracker.services.RegistrationService;

public class RegistrationServiceImpl implements RegistrationService{

    private RegistrationServiceImpl(){}

    public static class Holder{
        private final static RegistrationServiceImpl INSTANCE = new RegistrationServiceImpl();
    }

    public static RegistrationServiceImpl getInstance(){
        return RegistrationServiceImpl.Holder.INSTANCE;
    }

    public boolean registration(User user) {
        return false;
    }
}
