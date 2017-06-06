package by.hotelreservation.service.impl;

import by.hotelreservation.bean.Report;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.newdao.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl{
    @Autowired
	private ReportDao reportDao;

	public Report getFinancialReportInfoByMonth(Report report) throws ServiceException {
        try {
            report = reportDao.getFinancialReportInfoByMonth(report);
        }catch (DAOException e){
            new ServiceException(e);
        }
        return report;
    }

    public Report getFinancialReportInfoByQuarter(Report report) throws ServiceException{
        try {
            report = reportDao.getFinancialReportInfoByQuarter(report);
        }catch (DAOException e){
            new ServiceException(e);
        }
        return report;
    }

    public Report getRoomReportInfoByMonth(Report report) throws ServiceException{
        try {
            report = reportDao.getRoomReportInfoByMonth(report);
        }catch (DAOException e){
            new ServiceException(e);
        }
        return report;
    }

    public Report getRoomReportInfoByQuarter(Report report) throws ServiceException{
        try {
            report = reportDao.getRoomReportInfoByQuarter(report);
        }catch (DAOException e){
            new ServiceException(e);
        }
        return report;
    }
}