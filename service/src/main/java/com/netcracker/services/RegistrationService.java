package com.netcracker.services;

import com.netcracker.dao.beans.User;

public interface RegistrationService {
    boolean registration(User user);
}