package com.netcracker.dao.constants;

public class Constants {
    private Constants() {}

    public static final String GET_USER = "SELECT `id` FROM `db_hotelreservation`.`user` WHERE `login` = ? AND `password` = ?";

}
