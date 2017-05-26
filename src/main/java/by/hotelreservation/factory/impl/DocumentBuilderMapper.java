package by.hotelreservation.factory.impl;

import by.hotelreservation.factory.DocumentBuilderServiceFactory;
import by.hotelreservation.service.documentservice.DocumentBuilderService;
import by.hotelreservation.service.documentservice.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class DocumentBuilderMapper implements DocumentBuilderServiceFactory {

    final private static Map<String, DocumentBuilderService> builderMap = new HashMap();

    static {
        builderMap.put("RESERVATION_CONFIRM", new ReservationConfirmDocumentBuilderService());
        builderMap.put("FINANCIAL_REPORT", new FinancialReportBuilderService());
        builderMap.put("ROOM_REPORT", new RoomReportBuilderService());
        builderMap.put("RESERVATION_REPORT", new ReservationReportBuilderService());
        builderMap.put("ENTITY_CSV_REPORT", new EntityReportCsvBuilderService());
        builderMap.put("RESERVATION_VOUCHER", new ReservationVoucherBuilderService());
        builderMap.put("ROOM_DOCUMENT", new RoomDocumentBuilderService());
        builderMap.put("USER_DOCUMENT", new UserDocumentBuilderService());
        builderMap.put("ROOM_TYPE_DOCUMENT", new RoomTypeDocumentBuilderService());
    }

    private static class Holder{
        private final static DocumentBuilderMapper INSTANCE = new DocumentBuilderMapper();
    }

    public static DocumentBuilderMapper getInstance(){
        return Holder.INSTANCE;
    }

    public DocumentBuilderService getDocumentBuilderService(String documentName) {
        return builderMap.get(documentName.toUpperCase());
    }
}
