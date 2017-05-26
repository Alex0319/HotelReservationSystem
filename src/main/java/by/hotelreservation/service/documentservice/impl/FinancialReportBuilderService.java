package by.hotelreservation.service.documentservice.impl;

import by.hotelreservation.bean.DocumentObject;
import by.hotelreservation.bean.FinancialReport;
import by.hotelreservation.bean.Report;
import by.hotelreservation.builder.ReportBuilder;
import by.hotelreservation.documentbuilder.DocumentBuilder;
import by.hotelreservation.documentbuilder.impl.FinancialReportByMonthBuilder;
import by.hotelreservation.documentbuilder.impl.FinancialReportByQuarterBuilder;
import by.hotelreservation.service.documentservice.DocumentBuilderService;
import by.hotelreservation.service.exception.ServiceException;
import by.hotelreservation.service.impl.ReportServiceImpl;

import java.util.Map;

public class FinancialReportBuilderService implements DocumentBuilderService{
    private enum ReportType{
        BY_MONTH{
            @Override
            DocumentObject build(Report report) throws ServiceException{
                DocumentBuilder documentBuilder = FinancialReportByMonthBuilder.getInstance();
                report = new ReportServiceImpl().getFinancialReportInfoByMonth(report);
                return documentBuilder.buildDocument(report);
            }
        },

        BY_QUARTER{
            @Override
            DocumentObject build(Report report) throws ServiceException{
                DocumentBuilder documentBuilder = FinancialReportByQuarterBuilder.getInstance();
                report = new ReportServiceImpl().getFinancialReportInfoByQuarter(report);
                return documentBuilder.buildDocument(report);
            }
        };

        abstract DocumentObject build(Report report) throws ServiceException;
    }


    @Override
    public DocumentObject buildDocument(Map<String, String[]> documentParams) throws ServiceException {
        ReportType reportType = ReportType.valueOf(documentParams.get("type")[0].toUpperCase());
        return reportType.build(buildReport(documentParams));
    }

    private Report buildReport(Map<String, String[]> documentParams){
        return new ReportBuilder<FinancialReport>().year(documentParams.get("year")[0]).build();
    }
}
