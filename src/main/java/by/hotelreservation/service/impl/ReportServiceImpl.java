package by.hotelreservation.service.impl;

import by.hotelreservation.bean.Report;
import by.hotelreservation.dao.ReportDao;
import by.hotelreservation.dao.exception.DAOException;
import by.hotelreservation.dao.impl.ReportDaoImpl;
import by.hotelreservation.service.AbstractService;
import by.hotelreservation.service.exception.ServiceException;

import java.sql.Connection;

public class ReportServiceImpl extends AbstractService{
	private ReportDao reportDao = new ReportDaoImpl();

	public Report getFinancialReportInfoByMonth(Report report) throws ServiceException{
        Connection connection = null;
        try {
            connection = getConnection();
            report = reportDao.getFinancialReportInfoByMonth(report, connection);
        }catch (DAOException e){
            new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
        return report;
    }

    public Report getFinancialReportInfoByQuarter(Report report) throws ServiceException{
        Connection connection = null;
        try {
            connection = getConnection();
            report = reportDao.getFinancialReportInfoByQuarter(report, connection);
        }catch (DAOException e){
            new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
        return report;
    }

    public Report getRoomReportInfoByMonth(Report report) throws ServiceException{
        Connection connection = null;
        try {
            connection = getConnection();
            report = reportDao.getRoomReportInfoByMonth(report, connection);
        }catch (DAOException e){
            new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
        return report;
    }

    public Report getRoomReportInfoByQuarter(Report report) throws ServiceException{
        Connection connection = null;
        try {
            connection = getConnection();
            report = reportDao.getRoomReportInfoByQuarter(report, connection);
        }catch (DAOException e){
            new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
        return report;
    }
}