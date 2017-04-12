package com.netcracker.services.factory;

import com.netcracker.services.RegistrationService;

public abstract class AbstractServiceFactory {
    public abstract RegistrationService getRegistrationService();
}
