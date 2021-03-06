package by.hotelreservation.service;

import by.hotelreservation.service.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CrudServiceMapper {
    final private static Map<String, CrudService> services = new HashMap();

    static {
        services.put("DISCOUNT", new DiscountServiceImpl());
        services.put("RESERVATION", new ReservationServiceImpl());

        services.put("RESERVATION_ROOM", new ReservationRoomServiceImpl());
        services.put("ROOM", new RoomServiceImpl());
        services.put("ROOM_TYPE", new RoomTypeServiceImpl());
        services.put("USER", new UserServiceImpl());
        services.put("ROLE", new RoleServiceImpl());
        //services.put("REPORT", new ReportServiceImpl());
    }

    public static CrudService getService(String serviceName) {
        return services.get(serviceName.toUpperCase());
    }
}
