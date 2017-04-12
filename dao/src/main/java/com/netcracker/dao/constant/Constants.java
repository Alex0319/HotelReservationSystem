package com.netcracker.dao.constant;

public final class Constants {
    private Constants() {}
    public static final String GET_USER = "SELECT `id` FROM `db_hotelreservation`.`user` WHERE `login` = ? AND `password` = ?";
    public static final String GET_ALL_USERS = "SELECT `id`, `passport_number`, `name`, `surname`, `mobile_phone`, `login`, `password` FROM `db_hotelreservation`.`user`";
    public static final String ADD_USER = "INSERT INTO `db_hotelreservation`.`user` (`passport_number`, `name`, `surname`, `mobile_phone`, `password`) VALUES ('?', '?', '?', '?', '?', '?')";
    public static final String REMOVE_USER = "DELETE FROM `db_hotelreservation`.`user` WHERE `id`='?'";
    public static final String UPDATE_USER = "UPDATE `db_hotelreservation`.`user` SET `passport_number`='?', `name`='?', `surname`='?', `mobile_phone`='?', `password`='?' WHERE `id`='?'";

    public static final String GET_ALL_ROOMS = "SELECT `room`.`id`,`room_type`.`id`, `rooms_count`, `beds_count`, `cost_per_day`, `additional_info`, `floor`, `phone` " +
            "FROM (`db_hotelreservation`.`room` LEFT OUTER JOIN `db_hotelreservation`.`room_type` ON `room`.`id_room_type` = `room_type`.`id`)";
    public static final String ADD_ROOM = "INSERT INTO `db_hotelreservation`.`room` (`id_room_type`, `floor`, `phone`) VALUES ('?', '?', '?')";
    public static final String REMOVE_ROOM = "DELETE FROM `db_hotelreservation`.`room` WHERE `id`='?'";
    public static final String UPDATE_ROOM = "UPDATE `db_hotelreservation`.`room` SET `id_room_type`='?', `floor`='?', `phone`='?'";

    public static final String GET_ALL_RESERVATIONS = "SELECT `id`, `id_user`, `name`, `surname`, `room_number`, `date-in`, `date-out`, `days_count` " +
            "FROM (`db_hotelreservation`.`reservation` LEFT OUTER JOIN `db_hotelreservation`.`user` ON `reservation`.`id_user` = `user`.`id`)";
    public static final String ADD_RESERVATION = "INSERT INTO `db_hotelreservation`.`reservation` (`rooms_count`, `beds_count`, `cost_per_day`, `additional_info`) VALUES ('?', '?', '?', '?')";
    public static final String REMOVE_RESERVATION = "DELETE FROM `db_hotelreservation`.`reservation` WHERE `id`='?'";
    public static final String UPDATE_RESERVATION = "UPDATE `db_hotelreservation`.`reservation` SET `rooms_count`='?', `beds_count`='?', `cost_per_day`='?', `additional_info`='?' WHERE `id`='?'";
}
