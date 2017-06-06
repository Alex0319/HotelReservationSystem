package by.hotelreservation.newdao;

import by.hotelreservation.bean.Report;
import by.hotelreservation.exception.DAOException;

public interface ReportDao {
    Report getFinancialReportInfoByMonth(Report report) throws DAOException;
    Report getFinancialReportInfoByQuarter(Report report) throws DAOException;
    Report getRoomReportInfoByQuarter(Report report) throws DAOException;
    Report getRoomReportInfoByMonth(Report report) throws DAOException;
}
