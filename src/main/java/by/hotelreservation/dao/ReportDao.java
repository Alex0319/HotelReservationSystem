package by.hotelreservation.dao;

import by.hotelreservation.bean.Report;
import by.hotelreservation.dao.exception.DAOException;

import java.sql.Connection;

public interface ReportDao {
    Report getFinancialReportInfoByMonth(Report report, Connection connection) throws DAOException;
    Report getFinancialReportInfoByQuarter(Report report, Connection connection) throws DAOException;
    Report getRoomReportInfoByQuarter(Report report, Connection connection) throws DAOException;
    Report getRoomReportInfoByMonth(Report report, Connection connection) throws DAOException;
}
