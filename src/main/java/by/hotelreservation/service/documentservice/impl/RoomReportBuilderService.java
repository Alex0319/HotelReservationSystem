package by.hotelreservation.service.documentservice.impl;

import by.hotelreservation.bean.DocumentObject;
import by.hotelreservation.bean.Report;
import by.hotelreservation.bean.RoomReport;
import by.hotelreservation.builder.ReportBuilder;
import by.hotelreservation.documentbuilder.DocumentBuilder;
import by.hotelreservation.documentbuilder.impl.RoomReportByMonthBuilder;
import by.hotelreservation.documentbuilder.impl.RoomReportByQuarterBuilder;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.service.documentservice.DocumentBuilderService;
import by.hotelreservation.service.impl.ReportServiceImpl;

import java.util.Map;

public class RoomReportBuilderService implements DocumentBuilderService{
    private enum ReportType{
        BY_MONTH{
            @Override
            DocumentObject build(Report report) throws ServiceException {
                DocumentBuilder documentBuilder = RoomReportByMonthBuilder.getInstance();
                report = new ReportServiceImpl().getRoomReportInfoByMonth(report);
                return documentBuilder.buildDocument(report);
            }
        },

        BY_QUARTER{
            @Override
            DocumentObject build(Report report) throws ServiceException{
                DocumentBuilder documentBuilder = RoomReportByQuarterBuilder.getInstance();
                report = new ReportServiceImpl().getRoomReportInfoByQuarter(report);
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
        return new ReportBuilder<RoomReport>().year(documentParams.get("year")[0]).build();
    }
}
