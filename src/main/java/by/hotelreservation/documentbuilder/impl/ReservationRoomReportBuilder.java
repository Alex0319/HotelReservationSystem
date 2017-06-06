package by.hotelreservation.documentbuilder.impl;

import by.hotelreservation.bean.entity.Reservation;
import by.hotelreservation.documentbuilder.ExcelDocumentBuilder;
import by.hotelreservation.exception.ServiceException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.util.List;

public class ReservationRoomReportBuilder extends ExcelDocumentBuilder<List<Reservation>>{
    public ReservationRoomReportBuilder() {
        super("Reservation report for user");
    }

    private static class Holder{
        private final static ReservationRoomReportBuilder INSTANCE = new ReservationRoomReportBuilder();
    }

    public static ReservationRoomReportBuilder getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    protected void fillDoc(HSSFWorkbook workbook,List<Reservation> documentData) throws ServiceException {
        HSSFSheet sheet = workbook.createSheet("Report");
        int columnsCount = addHeader(sheet, documentData.get(0));
        for (Reservation reservation : documentData) {
            fillRow(sheet, reservation);
        }
        CellRangeAddress region = new CellRangeAddress(0,0,0,columnsCount);
        sheet.addMergedRegion(region);
        for (int i=0; i< columnsCount; i++){
            sheet.autoSizeColumn(i);
        }
    }

    private void fillRow(HSSFSheet sheet, Reservation reservation) throws ServiceException{
        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
        setCellStyle(row.createCell(0)).setCellValue(reservation.getId());
        setCellStyle(row.createCell(1)).setCellValue(reservation.getDateIn().toString());
        setCellStyle(row.createCell(2)).setCellValue(reservation.getDateOut().toString());
        setCellStyle(row.createCell(3)).setCellValue(reservation.getUser().getSurname() + " " + reservation.getUser().getName());
        setCellStyle(row.createCell(4)).setCellValue(reservation.getCostAdditionalServices());
        setCellStyle(row.createCell(5)).setCellValue(reservation.getDiscount().getId());
    }

    private int addHeader(HSSFSheet sheet, Reservation reservation){
        createRowWithCells(sheet, "Брони пользователя " + reservation.getUser().getUserFullname());
        Row row = createRowWithCells(sheet, null);
        Field[] fields = reservation.getClass().getDeclaredFields();
        for (int i=0; i< fields.length; i++){
            setCellStyle(row.createCell(i)).setCellValue(fields[i].getName());
        }
        return fields.length - 1;
    }

    private Row createRowWithCells(HSSFSheet sheet, String cellValue){
        Row row = sheet.createRow(sheet.getRow(0) == null ? sheet.getLastRowNum() : sheet.getLastRowNum() + 1);
        Cell cell = row.createCell(0);
        cell.setCellValue(cellValue);
        setCellStyle(cell);
        setCellStyle(row.createCell(1));
        return row;
    }

    private Cell setCellStyle(Cell cell){
        CellStyle cellStyle = cell.getCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        return cell;
    }
}
