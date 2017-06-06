package by.hotelreservation.newdao.impl;

import by.hotelreservation.bean.Report;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.newdao.ReportDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static by.hotelreservation.newdao.constants.Constants.*;

@Repository
public class ReportDaoImpl implements ReportDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Report getFinancialReportInfoByMonth(Report report) throws DAOException {
        List list = entityManager.createNativeQuery(GET_FINANCIAL_REPORT_BY_MONTH_FOR_YEAR).setParameter(1, report.getYear()).getResultList();
        return null;
    }

    @Override
    public Report getFinancialReportInfoByQuarter(Report report) throws DAOException {
        List list = entityManager.createNativeQuery(GET_FINANCIAL_REPORT_BY_QUARTER_FOR_YEAR).setParameter(1, report.getYear()).getResultList();
        return null;
    }

    @Override
    public Report getRoomReportInfoByQuarter(Report report) throws DAOException {
        List list = entityManager.createNativeQuery(GET_ROOM_REPORT_BY_MONTH_FOR_YEAR).setParameter(1, report.getYear()).getResultList();
        return null;
    }

    @Override
    public Report getRoomReportInfoByMonth(Report report) throws DAOException {
        List list = entityManager.createNativeQuery(GET_ROOM_REPORT_BY_QUARTER_FOR_YEAR).setParameter(1, report.getYear()).getResultList();
        return null;
    }
}
