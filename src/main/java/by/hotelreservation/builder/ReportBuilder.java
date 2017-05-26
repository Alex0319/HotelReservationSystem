package by.hotelreservation.builder;

import by.hotelreservation.bean.Report;

public class ReportBuilder<T> {
    private String year;

    public ReportBuilder year(String year){
        this.year = year;
        return this;
    }

    public String getYear() {
        return year;
    }

    public Report<T> build(){
        return new Report<>(this);
    }
}
